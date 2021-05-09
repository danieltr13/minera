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
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SenderSemaforo {

   
    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws InterruptedException {
        JsonObject estado= new JsonObject();
        estado.addProperty("estado", "GO");
        try {
            send("paco", estado.toString());
            Thread.sleep(5000);
            send("juan", "Hola amigo");
        } catch (IOException | TimeoutException ex) {
            Logger.getLogger(SenderSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    public static void send(String route, String messages) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try ( Connection connection = factory.newConnection();  Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            String routingKey = route;
            String message = messages;

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

        }
    }
}
