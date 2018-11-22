package io.ensure.deepsea.common.service;

import java.util.List;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

public class MongoRepositoryWrapper {

	protected final MongoClient client;

	public MongoRepositoryWrapper(Vertx vertx, JsonObject config) {
		this.client = MongoClient.createShared(vertx, config);
	}
	
	protected void upsertSingle(JsonObject document, String collection, Handler<AsyncResult<String>> resultHandler) {
		client.save(collection, document, resultHandler);
	}
	
	protected Future<List<JsonObject>> selectDocuments(String collection, JsonObject query) {
		Future<List<JsonObject>> future = Future.future();
		client.find(collection, query, future.completer());
		
		return future;
	}
	
}
