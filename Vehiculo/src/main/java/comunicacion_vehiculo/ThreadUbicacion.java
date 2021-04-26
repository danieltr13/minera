/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion_vehiculo;

import ubicacion.Vehiculo;

/**
 *
 * @author jc
 */
public class ThreadUbicacion extends Thread{

    Vehiculo v;
    
    public ThreadUbicacion(Vehiculo v) {
    this.v = v;
    }
    
    @Override
    public void run() {
        ActualizarUbicacion au = new ActualizarUbicacion(v);
    }
    
}
