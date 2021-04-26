/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDC.control;

import Dominio.ReporteCongestion;
import Dominio.ReporteMaterial;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alfon
 */
public interface IDBC {
    //RM means reporte material
    public abstract boolean guardarRM(ReporteMaterial entidad);
    public abstract boolean actualizarRM(ReporteMaterial entidad);
    public abstract ReporteMaterial buscarporIDRM(long id);
    public abstract ArrayList<ReporteMaterial> buscarTodasRM();
    public abstract boolean eliminarRM(long id);
    public abstract List<ReporteMaterial> buscarComoRM(String busqueda);
    //RC means reporte congestiones
    public abstract boolean guardarRC(ReporteCongestion entidad);
    public abstract boolean actualizarRC(ReporteCongestion entidad);
    public abstract ReporteCongestion buscarporIDRC(long id);
    public abstract ArrayList<ReporteCongestion> buscarTodasRC();
    public abstract boolean eliminarRC(long id);
    public abstract List<ReporteCongestion> buscarComoRC(String busqueda);
}
