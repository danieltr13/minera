/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.google.gson.JsonObject;
import comunicacion.ConsumerCamionero;
import comunicacion.ConsumerCliente;
import comunicacion.ComunicacionManager;
import comunicacion.ConsumerSemaforo;
import comunicacion.ConsumerVehiculo;
import comunicacion.SenderNotificationTyrus;
import comunicacion.SenderSemaforo;
import comunicacion.SenderSemaforoTyrus;
import comunicacion.SenderVehiculoTyrus;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class test {

    public static void main(String[] args) throws TimeoutException, IOException {
        //ComunicacionSemaforo cs = new ComunicacionSemaforo();
        //cs.consumer();
        //ComunicacionCamionero cc= new ComunicacionCamionero();       
        //ComunicacionManager cm= new ComunicacionManager();
        //ComunicacionVehiculo cv = new ComunicacionVehiculo();
        //ConsumerCliente cc= new ConsumerCliente();
        
       /// SenderSemaforoTyrus senderS= new SenderSemaforoTyrus();
        SenderSemaforo senderS= new SenderSemaforo();
        senderS.send("semaforo1","GO");
        // cc.sendSemaforos("Hola desde el semaforo");
//        SenderVehiculoTyrus svt = new SenderVehiculoTyrus();
//         SenderNotificationTyrus senderNotificationTyrus= new SenderNotificationTyrus();
//         senderNotificationTyrus.sendNotificacion("Muy buenas aquí vegueta 777 comentando");
        //svt.sendUbications("Hola desde mtvs soy un vehiculo");
    }
}
