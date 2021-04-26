/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Congestiones;

import Reportes.IReportes;
import Reportes.Reporte;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alfon
 */
public class Congestiones implements ICongestiones{

    private final List<Integer> vehiculos;
    private final List<Integer> congestion;
    private final IReportes reportes;
   
    public Congestiones() {
        this.vehiculos = new ArrayList<>();
        this.congestion = new ArrayList<>();
        this.reportes= new Reporte();
    }

    @Override
    public void agregarVehiculo(Integer posicion) {
        vehiculos.add(posicion);

    }

    @Override
    public void agregarACongestiones(Integer posicion) {
        congestion.add(posicion);
    }

    @Override
    public boolean detectarCongestion(JsonObject json) {
        for (int i = 0; i < vehiculos.size()-1; i++) {
            if (congestion.isEmpty()) {
                agregarACongestiones(vehiculos.get(i));
            }
            if (distanciaVehiculos(this.congestion.get(i), vehiculos.get(i+1))) {
                agregarACongestiones(vehiculos.get(i+1));
            }
        }
        if (this.congestion.size()>=3) {
            this.crearReporteC(json);
            return true;
        }
       return false;
    }


    @Override
    public boolean distanciaVehiculos(Integer vehiculo1, Integer vehiculo2){
       int diferencia= vehiculo2-vehiculo1;
       return diferencia>=100;
   }

    @Override
    public boolean crearReporteC(JsonObject json) {
        this.reportes.agregarRC(json);
        return true;
    }

}
