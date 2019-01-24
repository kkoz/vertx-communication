package vertx.communication.sender;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.eventbus.EventBus;

public class SenderVerticle extends AbstractVerticle {
	
	@Override
	public void start(Future<Void> future) {
        System.out.println("Starting SenderVerticle...");
		Router router = Router.router(vertx);
		router.route("/").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type","text/html")
				.end("<h1>Hello!</h1>");
		});
		router.route("/send").handler(routingContext -> {
            final EventBus eventBus = vertx.eventBus();
            final String message = "This message was published";
            eventBus.publish("TEST", message);
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type","text/html")
				.end("<h1>Sent Message!</h1>");
		});
		vertx.createHttpServer()
			.requestHandler(router::accept).listen(8080,
			result -> {
				if (result.succeeded()) {
					future.complete();
				} else {
					future.fail(result.cause());
				}
			});
	}

}
