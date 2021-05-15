/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import comunicacion.ComunicacionCamionero;
import comunicacion.ComunicacionCliente;
import comunicacion.ComunicacionManager;
import comunicacion.ComunicacionSemaforo;
import comunicacion.ComunicacionVehiculo;

public class test {

    public static void main(String[] args) {
        //ComunicacionSemaforo cs = new ComunicacionSemaforo();
        //cs.consumer();
        //ComunicacionCamionero cc= new ComunicacionCamionero();       
        //ComunicacionManager cm= new ComunicacionManager();
        //ComunicacionVehiculo cv = new ComunicacionVehiculo();
        ComunicacionCliente cc= new ComunicacionCliente();
       // cc.sendSemaforos("Hola desde el semaforo");
    }
}
