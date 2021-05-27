/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipo1.minera;

import com.google.gson.JsonObject;
import minera.control.IDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alfon
 */
@RestController()
@RequestMapping("/reportes/")
public class ReportesAPI {

    private IDBC facade;

    @Autowired
    private ValidarUsuario validarUser;

    @Autowired
    public ReportesAPI(IDBC facade) {
        this.facade = facade;
    }

    @PostMapping("/materiales")
    @CrossOrigin(origins = "https://localhost:8443")
    public ResponseEntity<?> reporteMateriales(@RequestBody String tkn) {
        int index = ValidarUsuario.tkns.indexOf(tkn);
        JsonObject noUser = new JsonObject();
        noUser.addProperty("no hay token", index);
        if (index == -1) {
            return new ResponseEntity<>(noUser.toString(), HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(facade.buscarTodasRM(), HttpStatus.OK);
        }
    }

    @PostMapping("/congestiones")
    @CrossOrigin(origins = "https://localhost:8443")
    public ResponseEntity<?> reporteCongestiones(@RequestBody String tkn) {
        int index = ValidarUsuario.tkns.indexOf(tkn);
        JsonObject noUser = new JsonObject();
        noUser.addProperty("no hay token", index);
        if (index == -1) {
            return new ResponseEntity<>(noUser.toString(), HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(facade.buscarTodasRC(), HttpStatus.OK);
        }
    }

}
