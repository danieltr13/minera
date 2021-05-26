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
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author Alfon
 */
public class SenderVehiculoTyrus {
    private final Gson gson = new Gson();
    private final static String QUEUE_VEHICULO = "vehiculo_sender";

    public void sendUbications(String v) {
        BasicConfigurator.configure();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try ( Connection connection = factory.newConnection();  Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_VEHICULO, false, false, false, null);
            String message = gson.toJson(v);
            //System.out.println(" [x] Sent '" + message + "'");
            //channel.basicPublish("", QUEUE_NAME, null, message.getBytes()); 
            channel.basicPublish("", QUEUE_VEHICULO, null, message.getBytes());
        } catch (IOException | TimeoutException ex) {
            //Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
