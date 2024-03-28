package com.hackaboss.pruebatecnica2.logica;

import com.hackaboss.pruebatecnica2.persistencia.ControladoraPersistencia;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    private static Controladora instance;
    private Controladora(){
        
    }
    public static Controladora getInstance(){
        if(instance==null){
            instance = new Controladora();
        }
        return instance;
    }
    
    public void crearCiudadano(Ciudadano ciudadano) {
        controlPersis.crearCiudadano(ciudadano);
        
    }

    public Ciudadano ciudadanoExiste(String nombreIn, String apellidoIn, String dniIn) {
        
        List<Ciudadano> listaCiudadanos = controlPersis.traerCiudadanos();
        Ciudadano ciudadano = new Ciudadano();
        
        for(Ciudadano ciud : listaCiudadanos){
            if(ciud.getDni().equalsIgnoreCase(dniIn)
                    && ciud.getApellido().equalsIgnoreCase(apellidoIn)
                    && ciud.getNombre().equalsIgnoreCase(nombreIn)
                    ){
                        ciudadano.setId(ciud.getId());
                        ciudadano.setNombre(ciud.getNombre());
                        ciudadano.setApellido(ciud.getApellido());
                        ciudadano.setDni(ciud.getDni());
                        ciudadano.setListaTurnos(ciud.getListaTurnos());
            }
        }
        return ciudadano;
    }

    public List<Ciudadano> traerCiudadanos() {
        return controlPersis.traerCiudadanos();
    }
    
    public Ciudadano traerCiudadano(int id) {
        return controlPersis.traerCiudadano(id);
    }
    
    public void eliminarCiudadano(Ciudadano ciudadano) {
        controlPersis.eliminarCiudadano(ciudadano);
    }
    
    public void editarCiudadano(Ciudadano ciudadano) {
        controlPersis.editarCiudadano(ciudadano);
    }
    
    public List<Ciudadano> buscarPorDni(String dniBusq) {
        List<Ciudadano> listaCiudadanos = controlPersis.traerCiudadanos();
        List<Ciudadano> listaFiltrada = new ArrayList<>();
        Ciudadano ciudEncontrado = new Ciudadano();
        for(Ciudadano ciud: listaCiudadanos){
            if(ciud.getDni().equalsIgnoreCase(dniBusq)){
                ciudEncontrado.setId(ciud.getId());
                ciudEncontrado.setNombre(ciud.getNombre());
                ciudEncontrado.setApellido(ciud.getApellido());
                ciudEncontrado.setDni(ciud.getDni());
                ciudEncontrado.setListaTurnos(ciud.getListaTurnos());
                listaFiltrada.add(ciudEncontrado);
            }
        }
        return listaFiltrada;
    }
    
    
    
    // Controladores de Turno

    public void crearTurno(Turno turno) {
        controlPersis.crearTurno(turno);
    }
    
    public List<Turno> traerTurnos() {
        List<Turno> listaTurnos = controlPersis.traerTurnos();
        List<Turno> listaFiltrada;
        if(listaTurnos==null){
            listaFiltrada = null;
        } else {
            listaFiltrada= listaTurnos.stream()
                .sorted((t1, t2)->t1.getFecha().compareTo(t2.getFecha()))
                .collect(Collectors.toList());
        }
        return listaFiltrada;
    }

    public void eliminarTurno(Turno turno) {
        controlPersis.eliminarTurno(turno);
    }

    public Turno traerTurno(int id) {
        return controlPersis.traerTurno(id);
    }

    public void editarTurno(Turno turno) {
        controlPersis.editarTurno(turno);
    }

    public List<Turno> buscarPorFecha(LocalDate fecha) {
        return controlPersis.buscarPorFecha(fecha);
    }

    public int obtenerTotalTurnos() {
        return controlPersis.obtenerTotalTurnos();
    }

    public List<Turno> buscarPorEstado(List lista, int estadoIn) {
        List<Turno> listaTurnos = lista;
        List<Turno> listaFiltrada;
        if(listaTurnos==null){
            listaFiltrada = null;
        } else {
            listaFiltrada = listaTurnos.stream()
                            .filter(t -> t.getEstado() == estadoIn )
                            .toList();
        }
        return listaFiltrada;
    }

    public Long contarTurnos(List<Turno> lista) {
        List<Turno> listaTurnos = lista;
        Long totalHoy = listaTurnos.stream().count();
        return totalHoy;
    }

    public Long contarPendientes(List<Turno> lista) {
        List<Turno> listaTurnos = lista;
        Long pendientesHoy = listaTurnos.stream()
                    .filter(t -> t.getEstado()==1 )
                    .count();
        return pendientesHoy;
    }

    public Long contarAtendidos(List<Turno> lista) {
        List<Turno> listaTurnos = lista;
        Long atendidosHoy = listaTurnos.stream()
                    .filter(t -> t.getEstado()==2 )
                    .count();
        return atendidosHoy;
    }

    public List<Long> traerTotales(List<Turno> lista) {
        List<Turno> listaTurnos = lista; 
        List<Long> listaTotales = new ArrayList<>();
        Long total = contarTurnos(listaTurnos);
        Long pendientes = contarPendientes(listaTurnos);
        Long atendidos = contarAtendidos(listaTurnos);
        
        listaTotales.add(total);
        listaTotales.add(pendientes);
        listaTotales.add(atendidos);
        return listaTotales;
    }

    public List<Long> traerTramites() {
        List<Turno> lista = traerTurnos();
        Long totalConsultas = lista.stream()
                        .filter(t -> t.getTramite() == 1 )
                        .count();
        Long totalComercial = lista.stream()
                        .filter(t -> t.getTramite() == 2 )
                        .count();
        Long totalTesoreria = lista.stream()
                        .filter(t -> t.getTramite() == 3 )
                        .count();
        Long totalReclamos = lista.stream()
                        .filter(t -> t.getTramite() == 4 )
                        .count();
        
        List<Long> listaTramites = new ArrayList<>();
        listaTramites.add(totalConsultas);
        listaTramites.add(totalComercial);
        listaTramites.add(totalTesoreria);
        listaTramites.add(totalReclamos);
        
        return listaTramites;
    }

    
    
    
}
