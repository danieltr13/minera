/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import Reportes.IReportes;
import Reportes.Reporte;
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
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author Alfon
 */
public class ConsumerCamionero {

    private final static String QUEUE_NAME = "camionero";
    private static Gson gson = new Gson();
    private static IReportes reportes = new Reporte();

    public static void main(String[] args) {
        //esta linea de basic configurator nos ayuda con la config
        // del log4j y que no se quede trabado al guardar solo un valor
        // e intentar guardarlo en la bd
        BasicConfigurator.configure();
        consume();
    }

    private static void consume() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            DeliverCallback deliverCallback = (String consumerTag, Delivery delivery) -> {
                String reporte = "";
                reporte = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + reporte + "'");
                saveRM(reporte);
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException ex) {
            Logger.getLogger(ConsumerCamionero.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(ConsumerCamionero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void saveRM(String rm) {
        JsonObject json = gson.fromJson(rm, JsonObject.class);
        reportes.agregarRM(json);
    }
}
