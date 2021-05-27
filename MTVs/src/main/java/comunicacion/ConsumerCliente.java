/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import com.google.gson.Gson;
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
 * @author Alfon
 */
public class ConsumerCliente {

    private final static String QUEUE_CLIENTE = "cliente_consumer";
    private SenderSemaforo senderSemaforo;
    private final Gson gson = new Gson();

    public ConsumerCliente() {
        this.senderSemaforo = new SenderSemaforo();
        consumer();
    }

    public void consumer() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_CLIENTE, false, false, false, null);
            DeliverCallback deliverCallback = (String consumerTag, Delivery delivery) -> {
                try {
                    String d = " ";
                    d = new String(delivery.getBody(), StandardCharsets.UTF_8);
                    System.out.println(" [x] Received '" + d + "'");
                    String[] messages = d.split(",");
                    System.out.println(messages[1]);
                    this.senderSemaforo.send(messages[0], messages[1]);
                } catch (IOException ex) {
                    Logger.getLogger(ConsumerCliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TimeoutException ex) {
                    Logger.getLogger(ConsumerCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            };
            channel.basicConsume(QUEUE_CLIENTE, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException ex) {
            Logger.getLogger(ConsumerSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(ConsumerSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private JsonObject stringToJson(String json) {
        JsonObject jobject = gson.fromJson(json, JsonObject.class);
        return jobject;
    }

}
