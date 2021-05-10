/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

/**
 *
 * @author MSI GF63
 */
public class ComunicacionManager {
    
    private ComunicacionCamionero cCamionero;
    private ComunicacionSemaforo cSemaforo;
    private ComunicacionVehiculo cVehiculo;
    private ComunicacionCliente cCliente;
    
    public ComunicacionManager() {
        this.cCamionero= new ComunicacionCamionero();
        this.cSemaforo= new ComunicacionSemaforo(this);
        this.cVehiculo= new ComunicacionVehiculo(this);
        //this.cCliente= new ComunicacionCliente();
    }
    
    public void notifyClientVehiculo(String v){
        System.out.println(v);
        //cCliente.sendUbications(v);
    }
    
    public void notifyClientSemaforos(String v){
        System.out.println(v);
//        cCliente.sendSemaforos(v);
    }
    
   
    
    
}
