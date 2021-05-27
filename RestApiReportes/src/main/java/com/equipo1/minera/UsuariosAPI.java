/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipo1.minera;

import com.google.gson.JsonObject;
import minera.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alfon
 */
@RestController
@RequestMapping("/usuario/")
public class UsuariosAPI {

    @Autowired
    private ValidarUsuario validarUser;

    @PostMapping("/validar")
    @CrossOrigin(origins = "https://localhost:8443")
    public ResponseEntity<?> iniciarSesion(@RequestBody Usuario usuario) {

        String tkn = this.validarUser.validaUsuario(usuario);
        JsonObject json = new JsonObject();
        json.addProperty("token", tkn);
        ValidarUsuario.tkns.append(json.toString());
        System.out.println(ValidarUsuario.tkns.toString());
        if (tkn != null) {
            return new ResponseEntity<>(json.toString(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró el usuario", HttpStatus.UNAUTHORIZED);
        }
    }
}
