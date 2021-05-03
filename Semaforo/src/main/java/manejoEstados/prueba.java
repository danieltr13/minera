/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejoEstados;

import comunicacion.ConsumerSemaforo2;

/**
 *
 * @author jc
 */
public class prueba {
    
    public static void main(String[] args) {
        Semaforo s = new Semaforo();
        
        
        ActualizarEstado ae = new ActualizarEstado(s);
        ConsumerSemaforo2 consumer= new ConsumerSemaforo2("paco", s);
        consumer.start();        
    }
}
