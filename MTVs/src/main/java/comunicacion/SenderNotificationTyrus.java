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
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author Alfon
 */
public class SenderNotificationTyrus {

    private final Gson gson = new Gson();
    private final static String QUEUE_NOTIFY = "cliente_notify";

    public void sendNotificacion(String v) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try ( Connection connection = factory.newConnection();  Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NOTIFY, false, false, false, null);
//            String message = gson.toJson(v);
 
            channel.basicPublish("", QUEUE_NOTIFY, null, v.getBytes());
        } catch (IOException | TimeoutException ex) {
            //Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
