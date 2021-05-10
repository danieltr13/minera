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
public final class ComunicacionSemaforo {

    private final static String QUEUE_NAME = "semaforoSender";
    private ComunicacionManager cm;
    private static final String EXCHANGE_NAME = "topic_logs";
    
    public ComunicacionSemaforo(){
        consumer();
    }

    public ComunicacionSemaforo(ComunicacionManager cm) {
        this();
        this.cm = cm;
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
                System.out.println(" [x] Received '" + d + "'");
                cm.notifyClientSemaforos(d);
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException ex) {
            Logger.getLogger(ComunicacionSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(ComunicacionSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void send(String route, String messages) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            String routingKey = route;
            String message = messages;

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

        }
    }
}
