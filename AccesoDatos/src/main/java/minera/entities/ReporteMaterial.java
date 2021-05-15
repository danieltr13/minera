/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minera.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue(value = "1")
public class ReporteMaterial extends Reporte implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "tipo", length = 55)
    private String tipo;
    @Column(name = "cantidad")
    private Float cantidad;
    @Column(name = "vehiculo")
    private String vehiculo;

    public ReporteMaterial() {
    }

    public ReporteMaterial(String tipo, Float cantidad, String vehiculo) {
        super();
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.vehiculo = vehiculo;
    }

    public ReporteMaterial(String tipo, Float cantidad, String vehiculo, Long id) {
        super(id);
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.vehiculo = vehiculo;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReporteMaterial)) {
            return false;
        }
        ReporteMaterial other = (ReporteMaterial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ReporteMaterial{" + "id=" + this.id + ",tipo=" + tipo + ", cantidad=" + cantidad + ", vehiculo=" + vehiculo + '}';
    }

}
