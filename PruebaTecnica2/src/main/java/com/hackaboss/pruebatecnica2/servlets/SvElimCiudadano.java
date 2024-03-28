
package com.hackaboss.pruebatecnica2.servlets;

import com.hackaboss.pruebatecnica2.logica.Ciudadano;
import com.hackaboss.pruebatecnica2.logica.Controladora;
import com.hackaboss.pruebatecnica2.logica.Turno;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;


@WebServlet(name = "SvElimCiudadano", urlPatterns = {"/SvElimCiudadano"})
public class SvElimCiudadano extends HttpServlet {

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
        Ciudadano ciudEliminar = control.traerCiudadano(idEliminar);
        
        List<Turno> turnos = control.traerTurnos();
        List<Turno> turnosCiudPendientes = control.buscarPorEstado(turnos, 1);
        boolean tieneTurnos = false;
        if(!turnosCiudPendientes.isEmpty()){
            for(Turno turn: turnosCiudPendientes){
                if(turn.getCiudadano().getId()== idEliminar){
                    tieneTurnos = true;
                    JOptionPane.showMessageDialog(null, "No se puede eliminar el ciudadado, está vinculado a un turno.", 
                        "Error", 1);
                }
            }
        }
        
        if(!tieneTurnos){
            int confirmar = JOptionPane.showConfirmDialog(null, 
                    "Esta seguro que desea eliminar este ciudadano?",
                "OK_CANCEL_OPTION", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
            
            if(confirmar==0){
                ciudEliminar.setHabilitado(false);
                control.eliminarCiudadano(ciudEliminar);
                JOptionPane.showMessageDialog(null, "Ciudadano eliminado correctamente.", 
                    "Información", 1);
            }
        }
        
        response.sendRedirect("SvCiudadano");
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
