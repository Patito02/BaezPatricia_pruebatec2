package com.hackaboss.pruebatecnica2.persistencia;

import com.hackaboss.pruebatecnica2.logica.Ciudadano;
import com.hackaboss.pruebatecnica2.logica.Turno;
import com.hackaboss.pruebatecnica2.persistencia.exceptions.NonexistentEntityException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControladoraPersistencia {
    
    CiudadanoJpaController ciuJpa = new CiudadanoJpaController();
    TurnoJpaController turnJpa = new TurnoJpaController();

    public void crearCiudadano(Ciudadano ciudadano) {
        ciuJpa.create(ciudadano);
    }


    public List<Ciudadano> traerCiudadanos() {
        return ciuJpa.traerCiudadanosHabilitados();
    }
    
    public Ciudadano traerCiudadano(int id) {
        return ciuJpa.traerCiudadanoHabilitado(id);
    }
    
    public void eliminarCiudadano(Ciudadano ciudadano) {
        try {
            ciudadano.setHabilitado(false);
            ciuJpa.edit(ciudadano);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex) { 
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editarCiudadano(Ciudadano ciudadano) {
        try {
            ciuJpa.edit(ciudadano);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    // Controladores de Turno
    
    public void crearTurno(Turno turno) {
        turnJpa.create(turno);
    }

    public List<Turno> traerTurnos() {
        return turnJpa.traerTurnosHabilitados();
    }

    public void eliminarTurno(Turno turno) {
        try {
            turno.setHabilitado(false);
            turnJpa.edit(turno);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex) { 
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Turno traerTurno(int id) {
        return turnJpa.traerTurnoHabilitado(id);
    }

    public void editarTurno(Turno turno) {
        try {
            turnJpa.edit(turno);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Turno> buscarPorFecha(LocalDate fecha) {
        return turnJpa.findByFecha(fecha);
    }

    public int obtenerTotalTurnos() {
        return turnJpa.getTurnoCount();
    }

    
    
}
