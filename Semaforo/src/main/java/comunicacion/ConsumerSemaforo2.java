/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejoEstados.Estado;
import manejoEstados.Semaforo;

public class ConsumerSemaforo2 extends Thread{

    private static final String EXCHANGE_NAME = "topic_logs";
    private String id;
    private Semaforo semaforo;
    
    public ConsumerSemaforo2(String id, Semaforo semaforo){
        this.id=id;
        this.semaforo=semaforo;
    }
    
    @Override
    public void run(){
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            String queueName = channel.queueDeclare().getQueue();
            String msg = "33";
            if (msg.length() < 1) {
                System.err.println("Usage: ReceiveLogsTopic [binding_key]...");
                System.exit(1);
            }
            channel.queueBind(queueName, EXCHANGE_NAME, id);
            
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                /*System.out.println(" [x] Received '"
                        + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");*/
                semaforo.setEstado(this.obtenerEstado(message));
                semaforo.setCambioExt(true);
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException ex) {
            Logger.getLogger(ConsumerSemaforo2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(ConsumerSemaforo2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    private Estado obtenerEstado(String message){
        return Estado.STOP;
    }
}
