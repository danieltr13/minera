/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import BDC.control.FNegocio;
import Dominio.ReporteMaterial;
import BDC.control.IDBC;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
 

/**
 *
 * @author Alfon
 */
public class test {
    public static void main(String[] args) {
//        IDBC fachada= new FNegocio();
//        fachada.guardarRM(new ReporteMaterial("diamantes", 3 , "bocho"));
        JsonObject json = new JsonObject();
        Gson gson = new Gson();
//        json.append("material", "diamantes");
//        json.append("vehiculo", "bocho");
//        json.append("cantidad", "13");
//        System.out.println(json.get("material"));
        json.addProperty("tipo", "diamantes");
        json.addProperty("cantidad", 15);
        json.addProperty("vehiculo", "ferrari");
        System.out.println(jsonToRM(json));
        System.out.println(agregarCongestion(24, 200));
    }
   
   public static boolean agregarCongestion(Integer vehiculo1, Integer vehiculo2){
       int diferencia= vehiculo2-vehiculo1;
       return diferencia>=100;
   }
    
   public static ReporteMaterial jsonToRM(JsonObject json) {
        String tipo = json.get("tipo").getAsString();
        Integer cantidad = json.get("cantidad").getAsInt();
        String vehiculo = json.get("vehiculo").getAsString();
        return new ReporteMaterial(tipo, cantidad, vehiculo);
    }
}
