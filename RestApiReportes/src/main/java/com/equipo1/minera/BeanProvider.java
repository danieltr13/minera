/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipo1.minera;

import minera.control.FNegocio;
import minera.control.IDBC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alfon
 */
@Component
public class BeanProvider {
    
    @Bean
    @Scope("singleton")
    public IDBC fNegocio() {
        return new FNegocio();
    }
    
    @Bean
    @Scope("singleton")
    public ValidarUsuario validarUsuario() {
        return new ValidarUsuario();
    }

}
