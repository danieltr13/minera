/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import com.google.gson.Gson;
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
 * @author Alfon
 */
public class ComunicacionCliente {

    private final static String QUEUE_NAME = "semaforoSender";

    private static final String EXCHANGE_NAME = "topic_logs";
    private final Gson gson = new Gson();

    public ComunicacionCliente() {
        consumer();
    }

    public void consumer() {
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
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException ex) {
            Logger.getLogger(ComunicacionSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(ComunicacionSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendUbications(String v) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = gson.toJson(v);
            //System.out.println(" [x] Sent '" + message + "'");
            //channel.basicPublish("", QUEUE_NAME, null, message.getBytes()); 
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        } catch (IOException | TimeoutException ex) {
            //Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendSemaforos(String v) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = gson.toJson(v);
            //System.out.println(" [x] Sent '" + message + "'");
            //channel.basicPublish("", QUEUE_NAME, null, message.getBytes()); 
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        } catch (IOException | TimeoutException ex) {
            //Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
