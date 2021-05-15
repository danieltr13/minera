/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minera.control;

import minera.dao.RepositoryRM;
import minera.entities.ReporteMaterial;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alfon
 */
public class ControlReporteM implements BaseControl<ReporteMaterial> {

    private final RepositoryRM repositoryRM;
    private static ControlReporteM reporteM;

    private ControlReporteM() {
        this.repositoryRM = RepositoryRM.getRepositoryRM();
    }

    public static ControlReporteM getControlReporteM() {
        if (reporteM == null) {
            reporteM = new ControlReporteM();
        }
        return reporteM;
    }

    @Override
    public boolean guardar(ReporteMaterial entidad) {
        return this.repositoryRM.guardar(entidad);
    }

    @Override
    public boolean actualizar(ReporteMaterial entidad) {
        return this.repositoryRM.actualizar(entidad);
    }

    @Override
    public ReporteMaterial buscarporID(long id) {
        return this.repositoryRM.buscarporID(id);
    }

    @Override
    public ArrayList<ReporteMaterial> buscarTodas() {
        return this.repositoryRM.buscarTodas();
    }

    @Override
    public boolean eliminar(long id) {
        return this.repositoryRM.eliminar(id);
    }

    @Override
    public List<ReporteMaterial> buscarComo(String busqueda) {
        return this.repositoryRM.buscarComo(busqueda);
    }

}
