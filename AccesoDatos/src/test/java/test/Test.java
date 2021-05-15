/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import minera.control.FNegocio;
import minera.control.IDBC;
import minera.entities.ReporteMaterial;

/**
 *
 * @author Alfon
 */
public class Test {
    public static void main(String[] args) {
        IDBC facade = new FNegocio();
        facade.guardarRM(new ReporteMaterial("diamantes", 5f, "frente de bocho"));
    }
}
