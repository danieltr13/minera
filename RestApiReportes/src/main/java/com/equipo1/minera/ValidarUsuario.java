/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipo1.minera;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import minera.control.FNegocio;
import minera.control.IDBC;
import minera.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Alfon
 */
public class ValidarUsuario {
    
    static StringBuffer tkns = new StringBuffer();
    
    @Autowired
    private IDBC facade;

    public String validaUsuario(Usuario usuario) {
        Usuario user = facade.buscarPorNombre(usuario.getName()).get(0);
        if (usuario.getPassword().equals(user.getPassword()) && usuario.getName().equals(user.getName())) {
            return crearToken(user.getName());
        }
        return "usuario no encontrado";
    }

    private String crearToken(String user) {
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            token = JWT.create().withIssuer("auth0").
                    withClaim("usuario", user).sign(algorithm);
            verificarToken(token);
        } catch (JWTCreationException e) {
            System.err.println(e.getMessage());
        }
        return token;
    }

    private void verificarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verification = JWT.require(algorithm)
                    .withIssuer("auth0").build();
            DecodedJWT jwt = verification.verify(token);
            System.out.println(getUsername(token));
        } catch (JWTVerificationException e) {
            System.err.println(e.getMessage());
        }
    }

    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("usuario").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

}
