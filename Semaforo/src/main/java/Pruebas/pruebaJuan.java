/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import manejoEstados.*;
import comunicacion.ConsumerSemaforo;

/**
 *
 * @author jc
 */
public class pruebaJuan {
    
    public static void main(String[] args) {
        Semaforo s = new Semaforo();  
        ActualizarEstado ae = new ActualizarEstado(s);
        ConsumerSemaforo consumer= new ConsumerSemaforo("juan", s);
        consumer.start();        
    }
}
