/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


/**
 *
 * @author Alfon
 */
@Entity
@DiscriminatorValue(value = "2")
 public class ReporteCongestion extends Reporte implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "eventualidad", length = 255)
    private String eventualidad;
    @Column(name = "ubicacion")
    private int ubicacion;
    @Column(name = "causa")
    private String causa;

    public ReporteCongestion() {
    }

    public ReporteCongestion(String eventualidad, int ubicacion, String causa) {
        this.eventualidad = eventualidad;
        this.ubicacion = ubicacion;
        this.causa = causa;
    }

    public ReporteCongestion(String eventualidad, int ubicacion, String causa, Long id) {
        super(id);
        this.eventualidad = eventualidad;
        this.ubicacion = ubicacion;
        this.causa = causa;
    }
    
    public String getEventualidad() {
        return eventualidad;
    }

    public void setEventualidad(String eventualidad) {
        this.eventualidad = eventualidad;
    }

    public int getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReporteCongestion)) {
            return false;
        }
        ReporteCongestion other = (ReporteCongestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ReporteCongestion{"+ "idReporte"+this.id + "eventualidad=" + eventualidad + ", ubicacion=" + ubicacion + ", causa=" + causa + '}';
    }

}
