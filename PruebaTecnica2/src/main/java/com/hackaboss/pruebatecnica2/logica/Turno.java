package com.hackaboss.pruebatecnica2.logica;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Turno implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
   
    private LocalDate fecha;
    private LocalTime hora;
    private int tramite;
    private int estado;
    private boolean habilitado;
    
    @ManyToOne
    @JoinColumn(name="id_ciudadano")
    private Ciudadano ciudadano;

    public Turno() {
    }

    public Turno(LocalDate fecha, LocalTime hora, int tramite, int estado, boolean habilitado, Ciudadano ciudadano) {
        this.fecha = fecha;
        this.hora = hora;
        this.tramite = tramite;
        this.estado = estado;
        this.habilitado = habilitado;
        this.ciudadano = ciudadano;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    
    public int getTramite() {
        return tramite;
    }

    public void setTramite(int tramite) {
        this.tramite = tramite;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(Ciudadano ciudadano) {
        this.ciudadano = ciudadano;
    }

    @Override
    public String toString() {
        return "Turno{" + "id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", tramite=" + tramite + ", estado=" + estado + ", habilitado=" + habilitado + ", ciudadano=" + ciudadano + '}';
    }

    

    
}
