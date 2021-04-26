/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion_vehiculo;

import ubicacion.Vehiculo;

/**
 *
 * @author Dany
 */
public class Simulador {

    public static void main(String[] args) {
        Send send = new Send();
        Vehiculo vehiculo= new Vehiculo();
        //send.mandarUbi(vehiculo);
        ThreadUbicacion act= new ThreadUbicacion(vehiculo);
        act.start();
       
    }
}
