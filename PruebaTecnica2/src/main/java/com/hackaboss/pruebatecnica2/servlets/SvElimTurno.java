package com.hackaboss.pruebatecnica2.servlets;

import com.hackaboss.pruebatecnica2.logica.Controladora;
import com.hackaboss.pruebatecnica2.logica.Turno;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;


@WebServlet(name = "SvElimTurno", urlPatterns = {"/SvElimTurno"})
public class SvElimTurno extends HttpServlet {

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
        
        int idEliminar = Integer.parseInt(request.getParameter("id"));
        Turno turnEliminar = control.traerTurno(idEliminar);
        
        if(turnEliminar.getEstado()==2){
            JOptionPane.showMessageDialog(null, 
                    "No se puede cancelar este turno, ya fue atendido.", "Error", 1);
        } else {
            int confirmar = JOptionPane.showConfirmDialog(null, 
                    "Esta seguro que dese cancelar este turno?",
                "OK_CANCEL_OPTION", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
            
            if(confirmar==0){
                control.eliminarTurno(turnEliminar);
                JOptionPane.showMessageDialog(null, "Turno cancelado correctamente.", 
                    "Información", 1);
            }
        }
        
        response.sendRedirect("SvTurno");
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
