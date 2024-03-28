package com.hackaboss.pruebatecnica2.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ciudadano implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String dni;
    private String nombre;
    private String apellido;    
    private boolean habilitado;
    @OneToMany(mappedBy="ciudadano")
    private List<Turno> listaTurnos;

    public Ciudadano() {
    }

    public Ciudadano(String dni, String nombre, String apellido, boolean habilitado, List<Turno> listaTurnos) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.habilitado = habilitado;
        this.listaTurnos = listaTurnos;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
    

    public List<Turno> getListaTurnos() {
        return listaTurnos;
    }

    public void setListaTurnos(List<Turno> listaTurnos) {
        this.listaTurnos = listaTurnos;
    }

    @Override
    public String toString() {
        return "Ciudadano{" + "id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", habilitado=" + habilitado + '}';
    }

    

    
}
