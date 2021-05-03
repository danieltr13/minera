/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import BDC.control.FNegocio;
import BDC.control.IDBC;

/**
 *
 * @author Alfon
 */
public class test {

    public static void main(String[] args) {
        IDBC fachada = new FNegocio();
        System.out.println(fachada.buscarComoRC("280916,483057,693725,"));
    }

}
