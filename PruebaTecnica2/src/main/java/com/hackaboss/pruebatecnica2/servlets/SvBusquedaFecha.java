package com.hackaboss.pruebatecnica2.servlets;

import com.hackaboss.pruebatecnica2.logica.Controladora;
import com.hackaboss.pruebatecnica2.logica.Turno;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;


@WebServlet(name = "SvBusquedaFecha", urlPatterns = {"/SvBusquedaFecha"})
public class SvBusquedaFecha extends HttpServlet {

    Controladora control = Controladora.getInstance();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        LocalDate fechaIn = LocalDate.parse(request.getParameter("fechaBusqueda"));
        int estadoIn = Integer.parseInt(request.getParameter("selectEstado"));
        
        List<Turno> listaTurnos = control.buscarPorFecha(fechaIn);
        HttpSession misesion = request.getSession();

        if(!listaTurnos.isEmpty()){
            if(estadoIn == 0){
                misesion.setAttribute("turnos", listaTurnos);
            } else {
                List<Turno> listaFiltrada = control.buscarPorEstado(listaTurnos, estadoIn);
                if(listaFiltrada.isEmpty()){
                    JOptionPane.showMessageDialog(null, "No hay turnos con los datos requeridos.", "Información", 1);
                } else {
                    misesion.setAttribute("turnos", listaFiltrada);
                }
            }
        } else{
            JOptionPane.showMessageDialog(null, "No hay turnos para la fecha seleccionada.", "Información", 1);
        }
        
        response.sendRedirect("verTurnos.jsp");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession misesion = request.getSession();
        LocalDate fechaIn = LocalDate.parse(request.getParameter("fechaBusqueda"));
        List<Turno> listaTurnosHoy = control.buscarPorFecha(fechaIn);
        
        if(listaTurnosHoy.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay turnos para la fecha seleccionada.", "Información", 1);
        } else {
            List<Turno> listaFiltrada = control.buscarPorEstado(listaTurnosHoy, 1);
            misesion.setAttribute("turnos", listaFiltrada);
        }
        response.sendRedirect("turnosPendientes.jsp");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
