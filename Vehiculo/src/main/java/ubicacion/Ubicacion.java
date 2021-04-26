/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubicacion;

/**
 *
 * @author jc
 */
public class Ubicacion {
        
    private int longitud;
    private int latitud;
    private int altitud;

    public Ubicacion(int longitud, int latitud, int altitud) {
        this.longitud = longitud;
        this.latitud = latitud;
        this.altitud = altitud;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public int getAltitud() {
        return altitud;
    }

    public void setAltitud(int altitud) {
        this.altitud = altitud;
    }

    @Override
    public String toString() {
        return "Ubicacion{" + "longitud=" + longitud + ", latitud=" + latitud + ", altitud=" + altitud + '}';
    }            
}
