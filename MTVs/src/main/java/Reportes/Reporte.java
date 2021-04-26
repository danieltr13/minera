/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import BDC.control.FNegocio;
import BDC.control.IDBC;
import Dominio.ReporteCongestion;
import Dominio.ReporteMaterial;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


/**
 *
 * @author Alfon
 */
public class Reporte implements IReportes {

    private final IDBC fNegocio;
    private final Gson gson;

    public Reporte() {
        this.fNegocio = new FNegocio();
        this.gson = new Gson();
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
        Integer cantidad = json.get("cantidad").getAsInt();
        String vehiculo = json.get("vehiculo").getAsString();
        return new ReporteMaterial(tipo, cantidad, vehiculo);
    }

    public ReporteCongestion jsonToRC(JsonObject json) {
        String eventualidad = json.get("eventualidad").getAsString();
        String causa = json.get("causa").getAsString();
        Integer ubicacion = json.get("ubicacion").getAsInt();
        return new ReporteCongestion(eventualidad, ubicacion, causa);
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
