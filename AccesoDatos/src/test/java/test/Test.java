/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import minera.control.FNegocio;
import minera.control.IDBC;
import minera.entities.ReporteMaterial;
import minera.entities.Usuario;

/**
 *
 * @author Alfon
 */
public class Test {
    private static IDBC facade = new FNegocio();
    public static void main(String[] args) {
        
        System.out.println(validaUsuario("juan"));
        
    }
    
    public static Usuario validaUsuario(String username) {
        Usuario user = facade.buscarPorNombre(username).get(0);
        System.out.println("usuario "+ user.toString());
        return user;
    }
}
