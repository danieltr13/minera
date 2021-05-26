package sender;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI GF63
 */
public class SenderSemaforo {
    private final static String QUEUE_NAME = "cliente_consumer";

    public SenderSemaforo() {
    }

    public static void send(String messages) {
       ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try ( Connection connection = factory.newConnection();  Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = messages;

            //System.out.println(" [x] Sent '" + message + "'");
            //channel.basicPublish("", QUEUE_NAME, null, message.getBytes()); 
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        } catch (IOException | TimeoutException ex) {
            Logger.getLogger(SenderSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
