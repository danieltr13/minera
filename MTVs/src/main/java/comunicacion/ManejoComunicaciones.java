/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import java.io.IOException;

/**
 *
 * @author Alfon
 */
public class ManejoComunicaciones {

    private Sockets sockets;
    private ComunicacionCliente cliente;

    public ManejoComunicaciones() {
        this.sockets = new Sockets(this);
        this.cliente = new ComunicacionCliente();
    }

    public void notificarCambioEstado(byte[] semaforo) {
        cliente.notificarCambioEstado(semaforo);
    }

    public void cambiarEstado(byte[] semaforo) throws IOException {
        sockets.cambiarEstado(semaforo);
    }

}
