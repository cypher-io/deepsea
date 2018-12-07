package io.ensure.deepsea.ui.menu;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@VertxGen
@ProxyGen
public interface MenuService {

	@Fluent
	MenuService initializePersistence(Handler<AsyncResult<Void>> resultHandler);

	@Fluent
	MenuService addMenu(MenuItem menuItem, Handler<AsyncResult<MenuItem>> resultHandler);

	@Fluent
	MenuService retrieveSubMenu(String id, Handler<AsyncResult<MenuItem>> resultHandler);
	
	@Fluent
	MenuService retrieveMenu(Handler<AsyncResult<MenuItem>> resultHandler);
	
}