package io.ensure.deepsea.product;

import io.ensure.deepsea.common.BaseMicroserviceVerticle;
import io.ensure.deepsea.common.config.ConfigRetrieverHelper;
import io.ensure.deepsea.common.helper.RedisHelper;
import io.ensure.deepsea.product.api.RestProductAPIVerticle;
import io.ensure.deepsea.product.impl.MySqlProductServiceImpl;
import io.vertx.config.ConfigRetriever;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.serviceproxy.ServiceBinder;

public class ProductVerticle extends BaseMicroserviceVerticle {

	/**
	 * The name of the event bus service.
	 */
	private static final String SERVICE_NAME = "product-eb-service";

	/**
	 * The address on which the service is published.
	 */
	private static final String SERVICE_ADDRESS = "service.product";

	private Logger log = LoggerFactory.getLogger(getClass());

	private ProductService productService;

	@Override
	public void start(Future<Void> future) throws Exception {
		super.start();
		ConfigRetriever retriever = ConfigRetriever
				.create(vertx, new ConfigRetrieverHelper()
						.getOptions("deepsea", "deepsea-product"));
		        retriever.getConfig(res -> {
		        	if (res.succeeded()) {
		
				// create the service instance
		        		JsonObject mySqlConfig = new JsonObject()
		        				.put("host", res.result().getString("database.host"))
								.put("port", res.result().getInteger("database.port"))
								.put("username", System.getenv("DB_USERNAME"))
								.put("password", System.getenv("DB_PASSWORD"))
								.put("database", System.getenv("DB_NAME"));
				
				RedisHelper.getRedisOptions(vertx, "deepsea-product").setHandler(redisRes -> {
					if (redisRes.succeeded()) {
						productService = new MySqlProductServiceImpl(vertx, mySqlConfig, redisRes.result());
						// Register the handler
						new ServiceBinder(vertx).setAddress(SERVICE_ADDRESS).register(ProductService.class, productService);
				
						initProductDatabase(productService);
				
						// publish the service and REST endpoint in the discovery infrastructure
						publishEventBusService(SERVICE_NAME, SERVICE_ADDRESS, ProductService.class)
								.compose(servicePublished -> deployRestVerticle()).setHandler(future.completer());

					} else {
						log.error("Redis Config not found");
						future.fail(redisRes.cause());
					}
				});
				
				
				
        	} else {
        		log.error("Unable to find config map for deepsea-shared MySQL");
        	}
        
        });
		

	}

	private Future<Void> initProductDatabase(ProductService service) {
		Future<Void> initFuture = Future.future();
		service.initializePersistence(initFuture.completer());
		return initFuture.map(v -> null);
	}

	private Future<Void> deployRestVerticle() {
		Future<String> future = Future.future();
		vertx.deployVerticle(new RestProductAPIVerticle(productService),
				new DeploymentOptions().setConfig(config()), future.completer());
		return future.map(r -> null);
	}

}
