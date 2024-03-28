package com.hackaboss.pruebatecnica2.servlets;

import com.hackaboss.pruebatecnica2.logica.Controladora;
import com.hackaboss.pruebatecnica2.logica.Turno;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;


@WebServlet(name = "SvTurnosPendientes", urlPatterns = {"/SvTurnosPendientes"})
public class SvTurnosPendientes extends HttpServlet {

    Controladora control = Controladora.getInstance();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession misesion = request.getSession();
        List<Turno> listaTurnos = control.traerTurnos();
        
        if(listaTurnos != null){
            List<Turno> listaFiltrada = control.buscarPorEstado(listaTurnos, 1);

            if(listaFiltrada.isEmpty()){
                JOptionPane.showMessageDialog(null, "No hay turnos pendientes para hoy", "Información", 1);
                response.sendRedirect("SvInicio");
            } else {
                misesion.setAttribute("turnos", listaFiltrada);
                response.sendRedirect("turnosPendientes.jsp");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay turnos pendientes para hoy", "Información", 1);
            response.sendRedirect("SvInicio");
        }
        
        
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int idEditar = Integer.parseInt(request.getParameter("id"));
        Turno turnModif = control.traerTurno(idEditar);
        
        int confirmar = JOptionPane.showConfirmDialog(null, "Esta seguro que desea atender este turno?",
                "Atender Turno", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
            
        if(confirmar==0){
            turnModif.setEstado(2);
            JOptionPane.showMessageDialog(null, "Turno atendido.", 
                    "Información", 1);
            turnModif.setEstado(2);
            control.editarTurno(turnModif);
        }
        
        response.sendRedirect("SvTurnosPendientes");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
