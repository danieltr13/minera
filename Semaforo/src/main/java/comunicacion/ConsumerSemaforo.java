/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
import org.apache.log4j.BasicConfigurator;

public class ConsumerSemaforo extends Thread{

    private static final String EXCHANGE_NAME = "topic_logs";
    private String id;
    private Semaforo semaforo;
    private Gson gson;
    
    public ConsumerSemaforo(String id, Semaforo semaforo){
        this.id=id;
        this.semaforo=semaforo;
        this.gson=new Gson();
        BasicConfigurator.configure();
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
            channel.queueBind(queueName, EXCHANGE_NAME, id);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(message);
                System.out.println(this.obtenerEstado(message));
                semaforo.setEstado(this.obtenerEstado(message));
                semaforo.setCambioExt(true);
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException ex) {
            Logger.getLogger(ConsumerSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(ConsumerSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    private Estado obtenerEstado(String message){
        if (message.equalsIgnoreCase(Estado.GO.toString())) {
            System.out.println("cambiando a go");
            return Estado.GO;
        }else if (message.equalsIgnoreCase(Estado.CAUTION.toString())) {
            System.out.println("cambiando a stop");
            return Estado.CAUTION;
        }
        System.out.println("cambiando a stop");
        return Estado.STOP;
    }
    
    private JsonObject stringToJson(String json) {
        JsonObject jVehiculo = gson.fromJson(json, JsonObject.class);
        return jVehiculo;
    }
    
}
