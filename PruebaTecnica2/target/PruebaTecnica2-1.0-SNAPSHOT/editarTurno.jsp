
<%@page import="com.hackaboss.pruebatecnica2.logica.Turno"%>
<%@page import="com.hackaboss.pruebatecnica2.logica.Ciudadano"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp" %> 
<%@include file="components/bodyinicial.jsp" %> 

<h2>Editar turno</h2>

<% Turno turn = (Turno) request.getSession().getAttribute("turnoEditar"); %>
<form class="user" action="SvModifTurno" method="POST">
    <p>Ingrese los datos de la persona: </p>
    
        <div class="col-sm-6 mb-3 mb-sm-0">
            <input type="hidden" class="form-control form-control-user"  name="idCiudadano"
                   value="<%=turn.getCiudadano().getId() %>" >
        </div>
    
    <div class="form-group row">
        <div class="col-sm-6 mb-3 mb-sm-0">
            <input type="text" class="form-control form-control-user"  name="nombre"
                   placeholder="Nombre" value="<%=turn.getCiudadano().getNombre() %>" disabled>
        </div>
        
    </div>
    <div class="form-group row">
        <div class="col-sm-6">
            <input type="text" class="form-control form-control-user"  name="apellido"
                   placeholder="Apellido" value="<%=turn.getCiudadano().getApellido() %>" disabled>
        </div>
    </div> 
    <div class="form-group row">
        <div class="col-sm-6">
            <input type="text" class="form-control form-control-user"  name="dni"
                   placeholder="DNI" value="<%=turn.getCiudadano().getDni() %>" disabled>
        </div>
    </div> 
    <p>Seleccione fecha, hora y trámite: </p>
    
    <div class="form-group row">
        <div class="col-sm-3 mb-3 mb-sm-0">
            <input type="date" class="form-control form-control-user" name="fecha" 
                   placeholder="Fecha" value="<%=turn.getFecha() %>" required>
        </div>
        <div class="col-sm-3 mb-3 mb-sm-0">
            <input type="time" class="form-control form-control-user" min="09:00" max="18:00" 
                   name="hora" value="<%=turn.getHora() %>" required>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm-6">
            <select class="form-control p-2" style="border-radius: 40px; font-size: 15px;" 
                    name="tramite" value="<%=turn.getTramite() %>" required>
                <option value="" selected>Seleccione un trámite...</option>
                <option <% if(turn.getTramite()==1) { %> selected <% } %> value="1">Consulta / Información</option>
                <option <% if(turn.getTramite()==2) { %> selected <% } %> value="2">Atención Comercial</option>
                <option <% if(turn.getTramite()==3) { %> selected <% } %> value="3">Atención Tesorería</option>
                <option <% if(turn.getTramite()==4) { %> selected <% } %> value="4">Reclamos</option>
            </select>
        </div>
    </div> 
        
    
    <button type="submit" class="btn btn-primary btn-user btn-block col-sm-6 mb-3">
        Modificar Turno
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
