/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minera.control;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alfon
 */
public interface BaseControl <T>{
    public abstract boolean guardar(T entidad);

    public abstract boolean actualizar(T entidad);

    public abstract T buscarporID(long id);

    public abstract ArrayList<T> buscarTodas();

    public abstract boolean eliminar(long id);

    public abstract List<T> buscarComo(String busqueda);
}
