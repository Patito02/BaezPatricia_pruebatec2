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


@WebServlet(name = "SvCiudadano", urlPatterns = {"/SvCiudadano"})
public class SvCiudadano extends HttpServlet {

    Controladora control = Controladora.getInstance();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession misesion = request.getSession();
        List<Ciudadano> listaCiudadanos = control.traerCiudadanos();
        
        if(listaCiudadanos.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay ciudadanos para mostrar.", "Información", 1);
            response.sendRedirect("SvInicio");
        } else {
            
            misesion.setAttribute("ciudadanos", listaCiudadanos);
            response.sendRedirect("verCiudadanos.jsp");
        }
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombreIn = request.getParameter("nombre");
        String apellidoIn = request.getParameter("apellido");
        String dniIn = request.getParameter("dni");
        
        List<Ciudadano> listaCiud = control.buscarPorDni(dniIn);
        Ciudadano ciudadano = new Ciudadano();
        
        if(!listaCiud.isEmpty()){
            JOptionPane.showMessageDialog(null, "Dni ya registrado en sistema", "Información", 1);
        } else {
            ciudadano.setNombre(nombreIn);
            ciudadano.setApellido(apellidoIn);
            ciudadano.setDni(dniIn.toUpperCase());
            ciudadano.setHabilitado(true);
            control.crearCiudadano(ciudadano);
            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente", "Información", 1);
        }
        
        response.sendRedirect("registrarCiudadano.jsp");
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
