/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

 
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import minera.control.FNegocio;
import minera.control.IDBC;
import minera.entities.ReporteCongestion;
import minera.entities.ReporteMaterial;

/**
 *
 * @author Alfon
 */
public class Reporte implements IReportes {

    private final IDBC fNegocio;
    private final Gson gson;
    private List<String> jsonVehiculo;
    private String key;
    private final HashMap<String, Integer> jsonCongestion;

    public Reporte() {
        this.key = "";
        this.jsonVehiculo = new ArrayList<>();
        this.jsonCongestion = new HashMap<>();
        this.fNegocio = new FNegocio();
        this.gson = new Gson();
    }

    public boolean addVehiculo(String vehiculo) {
        JsonObject jsonve = stringToJson(vehiculo);
        String matricula = jsonve.get("matricula").getAsString();
        addVehiculoCongestion(vehiculo);
        if (!jsonVehiculo.contains(matricula)) {
            jsonVehiculo.add(matricula);
            return true;
        }
        return false;
    }

    public boolean addVehiculoCongestion(String vehiculo) {
        JsonObject jsonve = stringToJson(vehiculo);
        ReporteCongestion rc = new ReporteCongestion();//jsonToRC(jsonve);
        String matricula = jsonve.get("matricula").getAsString();
        int ubicacion = jsonve.getAsJsonObject("ubicacion").get("latitud").getAsInt();
        rc.setUbicacion(ubicacion);
        rc.setMatriculas(matricula);
        if (!this.jsonCongestion.isEmpty()) {
            if (vehiculosDistancia(jsonCongestion.get(this.key), rc.getUbicacion())) {
                if (!jsonCongestion.containsKey(rc.getMatricula())) {
                    jsonCongestion.put(rc.getMatricula(), rc.getUbicacion());
                    return true;
                }
            }
        } else {
            this.key = rc.getMatricula();
            this.jsonCongestion.put(rc.getMatricula(), rc.getUbicacion());
        }
        if (jsonCongestion.size() >= 2) {
            agregarCongestion();
        }
        return false;
    }

    private void agregarCongestion() {
        StringBuilder stringBuilder = new StringBuilder();
        jsonCongestion.keySet().forEach(llave -> {
            stringBuilder.append(llave + ",");
        });
        if (fNegocio.buscarComoRC(stringBuilder.toString()).isEmpty()) {
            ReporteCongestion rc = new ReporteCongestion("Trafico", jsonCongestion.get(key), "Atracon de autos", stringBuilder.toString());
            fNegocio.guardarRC(rc);
            this.clearVehiculosCongestion();
            key = "";
        }
    }

    private static boolean vehiculosDistancia(Integer vehiculo1, Integer vehiculo2) {
        int diferencia = Math.abs(vehiculo2 - vehiculo1);
        return diferencia <= 50;
    }

    public void clearVehiculos() {
        this.jsonVehiculo.clear();
    }

    public void clearVehiculosCongestion() {
        this.jsonCongestion.clear();
    }

    public HashMap<String, Integer> getJsonCongestion() {
        return jsonCongestion;
    }

    public List<String> getJsonVehiculo() {
        return jsonVehiculo;
    }

    @Override
    public boolean agregarRM(JsonObject json) {
        ReporteMaterial reporteMaterial = jsonToRM(json);
        return fNegocio.guardarRM(reporteMaterial);
    }

    @Override
    public boolean agregarRC(JsonObject json) {
        ReporteCongestion reporteCongestion = jsonToRC(json);
        return fNegocio.guardarRC(reporteCongestion);
    }

    public ReporteMaterial jsonToRM(JsonObject json) {
        String tipo = json.get("tipo").getAsString();
        Float cantidad = json.get("cantidad").getAsFloat();
        String vehiculo = json.get("vehiculo").getAsString();
        return new ReporteMaterial(tipo, cantidad, vehiculo);
    }

    private JsonObject stringToJson(String json) {
        JsonObject jVehiculo = gson.fromJson(json, JsonObject.class);
        return jVehiculo;
    }

    public ReporteCongestion jsonToRC(JsonObject json) {
        String eventualidad = json.get("eventualidad").getAsString();
        String causa = json.get("causa").getAsString();
        String matricula = json.get("matricula").getAsString();
        Integer ubicacion = json.get("ubicacion").getAsInt();
        return new ReporteCongestion(eventualidad, ubicacion, causa, matricula);
    }

    @Override
    public boolean actualizarRM(JsonObject json) {
        ReporteMaterial reporteMaterial = jsonToRM(json);
        return fNegocio.actualizarRM(reporteMaterial);
    }

    @Override
    public String buscarporIDRM(JsonObject jsonID) {
        Long id = jsonID.get("id").getAsLong();
        return gson.toJson(fNegocio.buscarporIDRM(id));
    }

    @Override
    public String buscarTodasRM() {
        return gson.toJson(fNegocio.buscarTodasRM());
    }

    @Override
    public boolean eliminarRM(JsonObject jsonID) {
        Long id = jsonID.get("id").getAsLong();
        return fNegocio.eliminarRM(id);
    }

    @Override
    public String buscarComoRM(JsonObject busqueda) {
        String search = busqueda.get("busqueda").getAsString();
        return gson.toJson(fNegocio.buscarComoRM(search));
    }

    @Override
    public boolean actualizarRC(JsonObject json) {
        ReporteCongestion reporteC = jsonToRC(json);
        return fNegocio.actualizarRC(reporteC);
    }

    @Override
    public String buscarporIDRC(JsonObject jsonID) {
        Long id = jsonID.get("id").getAsLong();
        return gson.toJson(fNegocio.buscarporIDRC(id));
    }

    @Override
    public String buscarTodasRC() {
        return gson.toJson(fNegocio.buscarTodasRC());
    }

    @Override
    public boolean eliminarRC(JsonObject jsonID) {
        Long id = jsonID.get("id").getAsLong();
        return fNegocio.eliminarRC(id);
    }

    @Override
    public String buscarComoRC(JsonObject busqueda) {
        String search = busqueda.get("busqueda").getAsString();
        return gson.toJson(fNegocio.buscarComoRC(search));
    }

}
