
package com.hackaboss.pruebatecnica2.servlets;

import com.hackaboss.pruebatecnica2.logica.Ciudadano;
import com.hackaboss.pruebatecnica2.logica.Controladora;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;


@WebServlet(name = "SvBusquedaDni", urlPatterns = {"/SvBusquedaDni"})
public class SvBusquedaDni extends HttpServlet {

    Controladora control = Controladora.getInstance();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession misesion = request.getSession();
        String dniBusq = request.getParameter("dniBusqueda");
        
        List<Ciudadano> listaDni = control.buscarPorDni(dniBusq);
        if(listaDni == null || listaDni.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay ningún ciudadano con ese Dni.", "Información", 1);
            response.sendRedirect("SvCiudadano");
        } else {
            misesion.setAttribute("ciudadanos", listaDni);
            response.sendRedirect("verCiudadanos.jsp");
        }
        
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
