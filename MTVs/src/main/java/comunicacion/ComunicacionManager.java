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
 * @author MSI GF63
 */
public class ComunicacionManager {

    private ConsumerCamionero cCamionero;
    private ConsumerSemaforo cSemaforo;
    private ConsumerVehiculo cVehiculo;
    private ConsumerCliente cCliente;
    private SenderSemaforoTyrus semaforoTyrus;
    private SenderVehiculoTyrus vehiculoTyrus;
    private SenderSemaforo semaforo;

    public ComunicacionManager() {
        this.cCamionero = new ConsumerCamionero();
        this.cSemaforo = new ConsumerSemaforo(this);
        this.cVehiculo = new ConsumerVehiculo(this);
        this.cCliente= new ConsumerCliente();
        this.semaforoTyrus = new SenderSemaforoTyrus();
        this.semaforo = new SenderSemaforo();
        this.vehiculoTyrus= new SenderVehiculoTyrus();
    }

    public void notifyClientVehiculo(String v) {
        System.out.println(v);
        vehiculoTyrus.sendUbications(v);
    }

    public void notifyClientSemaforos(String v) {
        System.out.println(v);
        this.semaforoTyrus.sendSemaforos(v);
    }

    public void sendToSemaforo(String ruta,String mensaje){
        try {
            this.semaforo.send(ruta, mensaje);
        } catch (IOException | TimeoutException ex) {
            Logger.getLogger(ComunicacionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
