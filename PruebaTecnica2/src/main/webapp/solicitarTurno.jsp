<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp" %> 
<%@include file="components/bodyinicial.jsp" %> 

<h2>Solicitud de turno</h2>

<form class="user" action="SvTurno" method="POST">
    <p>Ingrese los datos de la persona: </p>
    <div class="form-group row">
        <div class="col-sm-6">
            <input type="text" class="form-control form-control-user uppercase" name="dni"
                   placeholder="DNI" style="text-transform: uppercase" required>
        </div>
    </div> 
    <p>Seleccione fecha, hora y trámite: </p>
    <div class="form-group row">
        <div class="col-sm-3 mb-3 mb-sm-0">
            <input type="date" class="form-control form-control-user" name="fecha" 
                   min="<%=LocalDate.now() %>" max="<%=LocalDate.now().plusDays(30) %>" required>
        </div>
        <div class="col-sm-3 mb-3 mb-sm-0">
            <input type="time" class="form-control form-control-user" name="hora"
                   min="09:00" max="17:30" required>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm-6">
            <select class="form-control p-2" style="border-radius: 40px; font-size: 15px;" 
                    name="tramite" required>
                <option value="" selected>Seleccione un trámite...</option>
                <option value="1">Consulta / Información</option>
                <option value="2">Atención Comercial</option>
                <option value="3">Atención Tesorería</option>
                <option value="4">Reclamos</option>
            </select>
        </div>
    </div> 
     
    
    <button type="submit" class="btn btn-primary btn-user btn-block col-sm-6 mb-3">
        Guardar Turno
    </button>
    
</form>
<form class="user" action="SvTurno" method="get">
    <button type="submit" class="btn btn-danger btn-user btn-block col-sm-6">
        Cancelar
    </button>
</form>
<hr>

<%@include file="components/footer.jsp" %>            
<%@include file="components/bodyfinal.jsp" %>  
