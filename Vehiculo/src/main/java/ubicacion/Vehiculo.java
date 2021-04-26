/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubicacion;

import java.util.Random;

/**
 *
 * @author jc
 */
public class Vehiculo {

    private Ubicacion ubi;
    private final String matricula;    

    public Vehiculo() {
        matricula = generarMatricula();
        this.ubi = new Ubicacion(0,0,0);
    }

    public Ubicacion getUbi() {
        return ubi;
    }

    public void setUbi(Ubicacion ubi) {
        this.ubi = ubi;
    }

    public String getMatricula() {
        return matricula;
    }

    private String generarMatricula() {

        Random rnd = new Random();
        int num = rnd.nextInt(999999);

        return String.format("%06d", num);
    }

    @Override
    public String toString() {
        return "Matricula: " + this.getMatricula()+ "\n" + this.getUbi().toString();
    }        
}
