package com.hackaboss.pruebatecnica2.servlets;

import com.hackaboss.pruebatecnica2.logica.Controladora;
import com.hackaboss.pruebatecnica2.logica.Turno;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

@WebServlet(name = "SvModifTurno", urlPatterns = {"/SvModifTurno"})
public class SvModifTurno extends HttpServlet {

    Controladora control = Controladora.getInstance();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idEditar = Integer.parseInt(request.getParameter("id"));
        Turno turno = control.traerTurno(idEditar);
        HttpSession misesion = request.getSession();

        if (turno.getEstado() == 2) {
            JOptionPane.showMessageDialog(null, "No se puede editar este turno, ya fue atendido.", "Error", 1);
            response.sendRedirect("SvTurno");
        } else {
            misesion.setAttribute("turnoEditar", turno);
            response.sendRedirect("editarTurno.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Turno turnModif = (Turno) request.getSession().getAttribute("turnoEditar");

        String fechaIn = request.getParameter("fecha");
        String horaIn = request.getParameter("hora");
        String tramiteIn = request.getParameter("tramite");

        if (turnModif.getEstado() == 2) {
            JOptionPane.showMessageDialog(null, "No se puede editar este turno, ya fue atendido.", "Error", 1);
        } else {

            turnModif.setFecha(LocalDate.parse(fechaIn));
            turnModif.setHora(LocalTime.parse(horaIn));
            turnModif.setTramite(Integer.parseInt(tramiteIn));

            control.editarTurno(turnModif);
        }
        response.sendRedirect("SvTurno");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
