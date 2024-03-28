
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


@WebServlet(name = "SvProximoTurno", urlPatterns = {"/SvProximoTurno"})
public class SvProximoTurno extends HttpServlet {

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
        List<Turno> listaPendientes = control.buscarPorEstado(listaTurnos, 1);
        if(listaPendientes.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay turnos pendientes para hoy", "Información", 1);
            response.sendRedirect("SvInicio");
        } else {
            Turno proxTurno = listaPendientes.get(0);
            misesion.setAttribute("proxTurno", proxTurno);
            response.sendRedirect("proximoTurno.jsp");
        }
        
        
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
