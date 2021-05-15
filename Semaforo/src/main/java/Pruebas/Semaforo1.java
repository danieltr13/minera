/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import comunicacion.ConsumerSemaforo;
import manejoEstados.ActualizarEstado;
import manejoEstados.Semaforo;

/**
 *
 * @author jc
 */
public class Semaforo1 {
    
    public static void main(String[] args) {
        Semaforo s = new Semaforo();  
        ActualizarEstado ae = new ActualizarEstado(s);
        ConsumerSemaforo consumer= new ConsumerSemaforo("semaforo1", s);
        consumer.start();        
    }
}
