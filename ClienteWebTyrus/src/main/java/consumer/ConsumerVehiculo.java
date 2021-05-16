/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumer;

import ClientTyrus.ClienteWebSocketVehiculo;
import ClientTyrus.ClienteWebSockete;
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
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author MSI GF63
 */
public class ConsumerVehiculo {

    private ClienteWebSocketVehiculo cliente;

    public ConsumerVehiculo(ClienteWebSocketVehiculo cliente) {
        this.cliente = cliente;
        this.consumer();
    }
    private final static String QUEUE_NAME = "vehiculo_sender";

    public void consumer() {
        BasicConfigurator.configure();
        try {
            // TODO code application logic here
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            DeliverCallback deliverCallback = (String consumerTag, Delivery delivery) -> {
                String d = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(d);
                this.cliente.enviarClientes(d);
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException ex) {
            Logger.getLogger(ClienteWebSockete.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(ClienteWebSockete.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
