package com.hackaboss.pruebatecnica2.servlets;

import com.hackaboss.pruebatecnica2.logica.Ciudadano;
import com.hackaboss.pruebatecnica2.logica.Controladora;
import com.hackaboss.pruebatecnica2.logica.Turno;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;


@WebServlet(name = "SvTurno", urlPatterns = {"/SvTurno"})
public class SvTurno extends HttpServlet {

    Controladora control = Controladora.getInstance();
     
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Turno> listaTurnos = control.traerTurnos();
        
        if(listaTurnos==null){
            JOptionPane.showMessageDialog(null, "No hay turnos para mostrar.", "Información", 1);
            response.sendRedirect("SvInicio");
        } else {
            HttpSession misesion = request.getSession();
            misesion.setAttribute("turnos", listaTurnos);
        
            response.sendRedirect("verTurnos.jsp");
        }

    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String dniIn = request.getParameter("dni");
        String fechaIn = request.getParameter("fecha");
        String horaIn = request.getParameter("hora");
        String tramiteIn = request.getParameter("tramite");
        
        List<Ciudadano> lCiudadano = control.buscarPorDni(dniIn);
        
        Ciudadano ciudadano = new Ciudadano();
        if(lCiudadano.isEmpty()){
            
            int confirmar = JOptionPane.showConfirmDialog(null, "Ciudadano no existe en sistema, desea crearlo?",
                "OK_CANCEL_OPTION", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
            
            if(confirmar==0){
                request.getRequestDispatcher("registrarCiudadano.jsp").forward(request, response);
               
            } else {
                request.getRequestDispatcher("solicitarTurno.jsp").forward(request, response);
            }
        } else {
            ciudadano = control.traerCiudadano(lCiudadano.get(0).getId());
        }
        
        
        Turno turno = new Turno();    
        turno.setFecha(LocalDate.parse(fechaIn));
        turno.setHora(LocalTime.parse(horaIn));
        turno.setTramite(Integer.parseInt(tramiteIn));
        turno.setEstado(1);
        turno.setHabilitado(true);
        turno.setCiudadano(ciudadano);
        control.crearTurno(turno);
        
        //Asocio el turno a la lista de turnos del ciudadano
        List<Turno> listaTurnosCiud = new ArrayList<>();
        listaTurnosCiud.add(turno);
        ciudadano.setListaTurnos(listaTurnosCiud);
        
        JOptionPane.showMessageDialog(null, "Turno registrado correctamente", "Información", 1);
        request.getRequestDispatcher("solicitarTurno.jsp").forward(request, response); 

    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
