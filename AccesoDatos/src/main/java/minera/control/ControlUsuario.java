/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minera.control;

import java.util.ArrayList;
import java.util.List;
import minera.dao.RepositoryUsuario;
import minera.entities.Usuario;

/**
 *
 * @author Alfon
 */
public class ControlUsuario implements BaseControl<Usuario> {

    private RepositoryUsuario RepositoryUsuario;
    private static ControlUsuario controlUsuario;

    private ControlUsuario() {
        this.RepositoryUsuario = RepositoryUsuario.getInstance();
    }
    
    public static ControlUsuario getInstance(){
         if (controlUsuario==null) {
             controlUsuario =  new ControlUsuario();
         }
        return controlUsuario;
    }
    
    @Override
    public boolean guardar(Usuario entidad) {
        return this.RepositoryUsuario.guardar(entidad);
    }

    @Override
    public boolean actualizar(Usuario entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario buscarporID(long id) {
        return this.RepositoryUsuario.buscarporID(id);
    }

    @Override
    public ArrayList<Usuario> buscarTodas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> buscarComo(String busqueda) {
        return this.RepositoryUsuario.buscarComo(busqueda);
    }

}
