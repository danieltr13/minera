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
import comunicacion.SenderSemaforo;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class test {

    public static void main(String[] args) {
        //ComunicacionSemaforo cs = new ComunicacionSemaforo();
        //cs.consumer();
        //ComunicacionCamionero cc= new ComunicacionCamionero();       
        //ComunicacionManager cm= new ComunicacionManager();
        //ComunicacionVehiculo cv = new ComunicacionVehiculo();
        ConsumerCliente cc= new ConsumerCliente();
        SenderSemaforo senderS= new SenderSemaforo();
        try {
            JsonObject json= new JsonObject();
            json.addProperty("estado", "STOP");
            senderS.send("semaforo1",json.toString());
            // cc.sendSemaforos("Hola desde el semaforo");
        } catch (IOException | TimeoutException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
