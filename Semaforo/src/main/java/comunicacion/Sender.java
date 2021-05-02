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
import java.util.logging.Level;
import java.util.logging.Logger;
import manejoEstados.Semaforo;

/**
 *
 * @author Alfon
 */
public class Sender {
    
    private final static String QUEUE_NAME = "semaforoSender";
    private final Gson gson = new Gson();
    
    public void mandar(Semaforo v) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try ( Connection connection = factory.newConnection();  Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = gson.toJson(v);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        } catch (IOException | TimeoutException ex) {
            Logger.getLogger(Semaforo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
