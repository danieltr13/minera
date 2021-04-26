/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion_vehiculo;

import ubicacion.Vehiculo;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author jc
 */
public class Send implements IComunicacionVehiculo{
    private final static String QUEUE_NAME = "vehiculo";

    Vehiculo v;

    public Send() {
    }

    public Vehiculo getV() {
        return v;
    }

    public void setV(Vehiculo v) {
        this.v = v;
    }
    
    @Override
    public void mandarUbi(Vehiculo v) {
         ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = v.getUbi().getLongitud()+"";
             //System.out.println(" [x] Sent '" + message + "'");
            for (int i = 0; i < 10; i++) {
                //channel.basicPublish("", QUEUE_NAME, null, message.getBytes()); 
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());  
            }
        } catch (IOException ex) {
            Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
