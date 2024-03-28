package com.hackaboss.pruebatecnica2.servlets;

import com.hackaboss.pruebatecnica2.logica.Ciudadano;
import com.hackaboss.pruebatecnica2.logica.Controladora;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;


@WebServlet(name = "SvModifCiudadano", urlPatterns = {"/SvModifCiudadano"})
public class SvModifCiudadano extends HttpServlet {

    Controladora control = Controladora.getInstance();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idEditar = Integer.parseInt(request.getParameter("id"));
        Ciudadano ciudadano = control.traerCiudadano(idEditar);
        HttpSession misesion = request.getSession();
        misesion.setAttribute("ciudadanoEditar", ciudadano);
        response.sendRedirect("editarCiudadano.jsp");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Ciudadano ciudEditar = (Ciudadano) request.getSession().getAttribute("ciudadanoEditar");
        
        String nombreIn = request.getParameter("nombre");
        String apellidoIn = request.getParameter("apellido");
        Ciudadano ciudadano = control.traerCiudadano(ciudEditar.getId());
        ciudadano.setNombre(nombreIn);
        ciudadano.setApellido(apellidoIn);
       
        control.editarCiudadano(ciudadano);
        JOptionPane.showMessageDialog(null, "Ciudadano modificado correctamente", "Información", 1);
        response.sendRedirect("SvCiudadano");
        
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
