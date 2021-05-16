/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipo1.minera;

 import minera.control.IDBC;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alfon
 */
@RestController()
@RequestMapping("/reportes/")
public class ReportesAPI {
    
    @Autowired
    private IDBC facade;
    
    @GetMapping("/materiales")
    @CrossOrigin(origins = "http://localhost:8080")
     public ResponseEntity<?> reporteMateriales() {
        return new ResponseEntity<>(facade.buscarTodasRM(), HttpStatus.OK);
    }
    
    @GetMapping("/congestiones")
    @CrossOrigin(origins = "http://localhost:8080")
     public ResponseEntity<?> reporteCongestiones() {
        return new ResponseEntity<>(facade.buscarTodasRC(), HttpStatus.OK);
    }

}
