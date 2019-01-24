package vertx.communication.receiver;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;

public class ReceiverVerticle extends AbstractVerticle {
	
	@Override
	public void start(Future<Void> future) {
        final EventBus eventBus = vertx.eventBus();
        eventBus.consumer("TEST", receivedMessage -> {
            System.out.println(receivedMessage.body());            
        });
        System.out.println("Starting ReceiverVerticle...");
	}
}
