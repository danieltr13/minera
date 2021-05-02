/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejoEstados;

import java.util.Random;

/**
 *
 * @author Dany
 */
public class Semaforo {
    private final String id;
    private Estado estado;
    //Ubicacion
    
    public Semaforo() {
        this.id = generarMatricula();      
        this.estado = Estado.GO;
    }

    public String getId() {
        return id;
    }    

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    private String generarMatricula() {

        Random rnd = new Random();
        int num = rnd.nextInt(9999);

        return String.format("%04d", num);
    }

    @Override
    public String toString() {
        return "Matricula: " + this.getId()+ "\n" + this.getEstado().toString();
    }       
    
}
