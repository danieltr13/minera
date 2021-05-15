/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import com.google.gson.JsonObject;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI GF63
 */
public final class ConsumerSemaforo {

    private final static String QUEUE_NAME = "semaforoSender";
    private ComunicacionManager cm;
     
    public ConsumerSemaforo(ComunicacionManager cm) {
        this.cm = cm;
        consumer();
    }
    
    public void consumer(){
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            DeliverCallback deliverCallback = (String consumerTag, Delivery delivery) -> {
                String d = " ";
                d = new String(delivery.getBody(), StandardCharsets.UTF_8);
                cm.notifyClientSemaforos(d);
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException ex) {
            Logger.getLogger(ConsumerSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(ConsumerSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
