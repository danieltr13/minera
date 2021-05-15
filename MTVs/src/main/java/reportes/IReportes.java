/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import com.google.gson.JsonObject;
 

/**
 *
 * @author Alfon
 */
public interface IReportes {

    public abstract boolean agregarRM(JsonObject json);

    public abstract boolean actualizarRM(JsonObject json);

    public abstract String buscarporIDRM(JsonObject id);

    public abstract String buscarTodasRM();

    public abstract boolean eliminarRM(JsonObject id);

    public abstract String buscarComoRM(JsonObject busqueda);

    //RC
    public abstract boolean agregarRC(JsonObject json);

    public abstract boolean actualizarRC(JsonObject json);

    public abstract String buscarporIDRC(JsonObject id);

    public abstract String buscarTodasRC();

    public abstract boolean eliminarRC(JsonObject id);

    public abstract String buscarComoRC(JsonObject busqueda);
}
