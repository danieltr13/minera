/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Congestiones.Congestiones;
import Congestiones.ICongestiones;
import Dominio.ReporteMaterial;
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
//        JsonObject json = new JsonObject();
        Gson gson = new Gson();
//        json.addProperty("tipo", "diamantes");
//        json.addProperty("cantidad", 15);
//        json.addProperty("vehiculo", "ferrari");
//        System.out.println(jsonToRM(json));
//        System.out.println(agregarCongestion(24, 200));
        ICongestiones con = new Congestiones();

        String json = gson.toJson(new vehiculo("adsasd", new ubicacion(12, 15, 16)));
        String json1 = gson.toJson(new vehiculo("321", new ubicacion(80, 15, 16)));
        String json3 = gson.toJson(new vehiculo("321", new ubicacion(80, 15, 16)));
        String json2 = gson.toJson(new vehiculo("asdas", new ubicacion(40, 15, 16)));
        JsonObject jVehiculo = gson.fromJson(json, JsonObject.class);
        System.out.println(jVehiculo.getAsJsonObject("ubicacion").get("latitud").getAsInt());

        con.agregarVehiculo(gson.fromJson(json, JsonObject.class));
        con.agregarVehiculo(gson.fromJson(json1, JsonObject.class));
        con.agregarVehiculo(gson.fromJson(json2, JsonObject.class));
        con.agregarVehiculo(gson.fromJson(json3, JsonObject.class));

    }

    public static boolean agregarCongestion(Integer vehiculo1, Integer vehiculo2) {
        int diferencia = vehiculo2 - vehiculo1;
        return diferencia >= 100;
    }

    public static ReporteMaterial jsonToRM(JsonObject json) {
        String tipo = json.get("tipo").getAsString();
        Integer cantidad = json.get("cantidad").getAsInt();
        String vehiculo = json.get("vehiculo").getAsString();
        return new ReporteMaterial(tipo, cantidad, vehiculo);
    }

    public static class ubicacion {

        public int latitud;
        public int y;
        public int z;

        public ubicacion(int x, int y, int z) {
            this.latitud = x;
            this.y = y;
            this.z = z;
        }

    }

    public static class vehiculo {

        public String matricula;
        public ubicacion ubicacion;

        public vehiculo(String matricula, ubicacion ubicacion) {
            this.matricula = matricula;
            this.ubicacion = ubicacion;
        }

    }

}
