/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejoEstados;

import comunicacion.Sender;
import java.util.Timer;
import java.util.TimerTask;
import static manejoEstados.Estado.CAUTION;
import static manejoEstados.Estado.GO;
import static manejoEstados.Estado.STOP;

/**
 *
 * @author jc
 */
public class ActualizarEstado {

    private final Semaforo semaforo;
    private final Timer timer;

    public ActualizarEstado(Semaforo semaforo) {
        this.semaforo = semaforo;
        timer = new Timer();
        NuevoEstado nuevoEstado = new NuevoEstado(semaforo);
        timer.scheduleAtFixedRate(nuevoEstado, 0, 2500);
    }
}

class NuevoEstado extends TimerTask {

    private final Semaforo semaforo;
    Sender send = new Sender();

    public NuevoEstado(Semaforo semaforo) {
        this.semaforo = semaforo;
    }
    
    //Verificar orden
    public void run() {
        //System.out.println(semaforo.toString());
        cambioEstado();
        send.mandar(semaforo);
        semaforo.setCambioExt(false);
    }

    private void cambioEstado() {
        Estado e = semaforo.getEstado();
        if (!semaforo.isCambioExt()) {
            if (null != e) {
                switch (e) {
                    case GO:
                        e = Estado.CAUTION;
                        break;
                    case CAUTION:
                        e = Estado.STOP;
                        break;
                    case STOP:
                        e = Estado.GO;
                        break;
                    default:
                        break;
                }
            }
        }
        semaforo.setEstado(e);
    }
}
