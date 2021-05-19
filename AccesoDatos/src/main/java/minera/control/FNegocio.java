/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minera.control;

import minera.entities.ReporteCongestion;
import minera.entities.ReporteMaterial;
import java.util.ArrayList;
import java.util.List;
import minera.entities.Usuario;

/**
 *
 * @author Alfon
 */
public class FNegocio implements IDBC {

    private final ControlReporteC reporteC;
    private final ControlReporteM reporteM;
    private final ControlUsuario controlUser;
    public FNegocio() {
         this.reporteC = ControlReporteC.getControlReporteC();
         this.reporteM = ControlReporteM.getControlReporteM();
         this.controlUser= ControlUsuario.getInstance();
    }

    @Override
    public boolean guardarRM(ReporteMaterial entidad) {
        return reporteM.guardar(entidad);
    }

    @Override
    public boolean actualizarRM(ReporteMaterial entidad) {
        return this.reporteM.actualizar(entidad);
    }

    @Override
    public ReporteMaterial buscarporIDRM(long id) {
        return this.reporteM.buscarporID(id);
    }

    @Override
    public ArrayList<ReporteMaterial> buscarTodasRM() {
        return this.reporteM.buscarTodas();
    }

    @Override
    public boolean eliminarRM(long id) {
        return this.reporteM.eliminar(id);
    }

    @Override
    public List<ReporteMaterial> buscarComoRM(String busqueda) {
        return this.reporteM.buscarComo(busqueda);
    }

    @Override
    public boolean guardarRC(ReporteCongestion entidad) {
        return this.reporteC.guardar(entidad);
    }

    @Override
    public boolean actualizarRC(ReporteCongestion entidad) {
        return this.reporteC.actualizar(entidad);
    }

    @Override
    public ReporteCongestion buscarporIDRC(long id) {
        return this.reporteC.buscarporID(id);
    }

    @Override
    public ArrayList<ReporteCongestion> buscarTodasRC() {
        return this.reporteC.buscarTodas();
    }

    @Override
    public boolean eliminarRC(long id) {
        return this.reporteC.eliminar(id);
    }

    @Override
    public List<ReporteCongestion> buscarComoRC(String busqueda) {
        return this.reporteC.buscarComo(busqueda);
    }

    @Override
    public boolean guardarUsuario(Usuario usuario) {
        return this.controlUser.guardar(usuario);
    }

    @Override
    public Usuario buscarPorID(long id) {
        return this.controlUser.buscarporID(id);
    }

    @Override
    public List<Usuario> buscarPorNombre(String nombre) {
        return this.controlUser.buscarComo(nombre);
    }

}
