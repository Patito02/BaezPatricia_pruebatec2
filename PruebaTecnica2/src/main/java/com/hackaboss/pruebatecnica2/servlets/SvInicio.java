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


@WebServlet(name = "SvInicio", urlPatterns = {"/SvInicio"})
public class SvInicio extends HttpServlet {

    Controladora control = Controladora.getInstance();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession misesion = request.getSession();
        LocalDate fechaHoy = LocalDate.now();
        List<Turno> listaTurnos = control.buscarPorFecha(fechaHoy);
        List<Long> totalesHoy = control.traerTotales(listaTurnos);
        misesion.setAttribute("totalesHoy", totalesHoy);
        
        List<Turno> listaTurnosTotal = control.traerTurnos();
        List<Long> totales = control.traerTotales(listaTurnosTotal);
        misesion.setAttribute("totales", totales);
        
        List<Long> listaTramites = control.traerTramites();
        misesion.setAttribute("listaTramites", listaTramites );
        
        response.sendRedirect("index.jsp");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
