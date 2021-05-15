/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minera.control;

import minera.dao.RepositoryRC;
import minera.entities.ReporteCongestion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alfon
 */
public class ControlReporteC implements BaseControl<ReporteCongestion>{
    private final RepositoryRC repositoryCongestion;
    private static ControlReporteC controlRC;
    
    private ControlReporteC() {
        this.repositoryCongestion= RepositoryRC.getRepositoryRC();
    }
    
    public static ControlReporteC getControlReporteC(){
        if (controlRC==null) {
            controlRC= new ControlReporteC();
        }
        return controlRC;
    }

    @Override
    public boolean guardar(ReporteCongestion entidad) {
        return this.repositoryCongestion.guardar(entidad);
    }

    @Override
    public boolean actualizar(ReporteCongestion entidad) {
        return this.repositoryCongestion.actualizar(entidad);
    }

    @Override
    public ReporteCongestion buscarporID(long id) {
        return this.repositoryCongestion.buscarporID(id);
    }

    @Override
    public ArrayList<ReporteCongestion> buscarTodas() {
        return this.repositoryCongestion.buscarTodas();
    }

    @Override
    public boolean eliminar(long id) {
        return this.repositoryCongestion.eliminar(id);
    }

    @Override
    public List<ReporteCongestion> buscarComo(String busqueda) {
        return this.repositoryCongestion.buscarComo(busqueda);
    }
}
