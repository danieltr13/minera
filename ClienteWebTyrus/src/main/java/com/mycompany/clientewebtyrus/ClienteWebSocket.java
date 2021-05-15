/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientewebtyrus;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import csac.ConsumerSemaforo;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author MSI GF63
 */
public class ClienteWebSocket {

    private static Object waitLock = new Object();
    private final static String QUEUE_NAME = "semaforo_sender_cliente";

    public static void main(String[] args) {
        WebSocketContainer container = null;//
        Session session = null;
        try {
            container = ContainerProvider.getWebSocketContainer();
            session = container.connectToServer(EndPoint.class,
                    URI.create("ws://localhost:8080/EjemploWS/websocketendpoint"));
            RemoteEndpoint.Basic basicRemote = session.getBasicRemote();
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
                    basicRemote.sendText(d);
                };
                channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
                });
            } catch (IOException ex) {
                Logger.getLogger(ClienteWebSocket.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TimeoutException ex) {
                Logger.getLogger(ClienteWebSocket.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (DeploymentException | IOException ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
