/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import Reportes.Reporte;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author Alfon
 */
public class ComunicacionVehiculo {

    private final static String QUEUE_NAME = "vehiculo";
    private final static Reporte reporteC = new Reporte();
    private ComunicacionManager cm;
    
    public ComunicacionVehiculo(){
       consumer();
    }
    
    public ComunicacionVehiculo(ComunicacionManager cm){
        this.cm=cm;
        consumer();
    }
    
    public void consumer(){
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
                //System.out.println(" [x] Received '" + d + "'");
                /*
                * Sale error después de un rato al correr 2 o mas vehiculos
                * System.out.println(reporteC.getJsonVehiculo());
                * reporteC.addVehiculo(d);
                */
                cm.notifyClientVehiculo(d);
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException ex) {
            Logger.getLogger(ComunicacionVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(ComunicacionVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //M
}
