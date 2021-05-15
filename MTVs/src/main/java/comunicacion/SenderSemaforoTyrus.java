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

/**
 *
 * @author Alfon
 */
public class SenderSemaforoTyrus {

    private final static String QUEUE_SEMAFORO = "semaforo_sender_cliente";
    private final Gson gson = new Gson();

    public void sendSemaforos(String v) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try ( Connection connection = factory.newConnection();  Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_SEMAFORO, false, false, false, null);
            String message = gson.toJson(v);
            //System.out.println(" [x] Sent '" + message + "'");
            channel.basicPublish("", QUEUE_SEMAFORO, null, v.getBytes());
        } catch (IOException | TimeoutException ex) {
            Logger.getLogger(ConsumerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
