/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minera.equipo1.Camionero;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author Alfon
 */
public class EnviarRMQ implements IComunicaciónCamionero {

    private final static String QUEUE_NAME = "camionero";
 
    public EnviarRMQ() {
     }

    @Override
    public void enviarReporte(String jReporte) {
        ConnectionFactory factory = new ConnectionFactory();
        BasicConfigurator.configure();
        factory.setHost("localhost");
        try ( Connection connection = factory.newConnection();  Channel channel = connection.createChannel()){
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, jReporte.getBytes());
        } catch (IOException | TimeoutException ex) {
            Logger.getLogger(EnviarRMQ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
