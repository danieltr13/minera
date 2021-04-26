package comunicacion_vehiculo;

import java.util.Timer;
import java.util.TimerTask;
import ubicacion.Ubicacion;
import ubicacion.Vehiculo;

/**
 *
 * @author jc
 */
public class ActualizarUbicacion {

    private final Vehiculo vehiculo;
    private final Timer timer;

    public ActualizarUbicacion(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        timer = new Timer();
        
        timer.scheduleAtFixedRate(new NuevaUbicacion(vehiculo), 0, 5000);

    }
}

class NuevaUbicacion extends TimerTask {

    private final Vehiculo vehiculo;  
    Send send = new Send();

    public NuevaUbicacion(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public void run() {        
        send.setV(vehiculo);
        movimiento();
        send.mandarUbi(vehiculo);
    }

    private void movimiento() {
        int lon = vehiculo.getUbi().getLongitud();
        int lat = vehiculo.getUbi().getLatitud();
        int alt = vehiculo.getUbi().getAltitud();
        
        Ubicacion ubi = new Ubicacion(lon + 10, lat + 10, alt + 10);
        this.vehiculo.setUbi(ubi);
    }
}
