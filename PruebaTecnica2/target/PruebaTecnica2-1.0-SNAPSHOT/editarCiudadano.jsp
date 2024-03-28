<%@page import="com.hackaboss.pruebatecnica2.logica.Turno"%>
<%@page import="com.hackaboss.pruebatecnica2.logica.Ciudadano"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp" %> 
<%@include file="components/bodyinicial.jsp" %> 

<h2>Editar datos del ciudadano</h2>

<% Ciudadano ciud = (Ciudadano) request.getSession().getAttribute("ciudadanoEditar"); %>
<form class="user" action="SvModifCiudadano" method="POST">
    <p>Ingrese los datos de la persona: </p>
    
        <div class="col-sm-6 mb-3 mb-sm-0">
            <input type="hidden" class="form-control form-control-user"  name="idCiudadano"
                   value="<%=ciud.getId() %>" >
        </div>
    <div class="form-group row">
        <div class="col-sm-6 mb-3 mb-sm-0">
            <input type="text" class="form-control form-control-user"  name="dni"
                   placeholder="Dni" value="<%=ciud.getDni() %>" disabled>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm-6 mb-3 mb-sm-0">
            <input type="text" class="form-control form-control-user"  name="nombre"
                   placeholder="Nombre" value="<%=ciud.getNombre() %>" required>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-sm-6">
            <input type="text" class="form-control form-control-user"  name="apellido"
                   placeholder="Apellido" value="<%=ciud.getApellido() %>" required>
        </div>
    </div> 

    <button type="submit" class="btn btn-primary btn-user btn-block col-sm-6 mb-3">
        Modificar Ciudadano
    </button>
    
</form>
<form class="user" action="SvCiudadano" method="get">
    <button type="submit" class="btn btn-danger btn-user btn-block col-sm-6">
        Cancelar
    </button>
</form> 
        <hr>

<%@include file="components/footer.jsp" %>            
<%@include file="components/bodyfinal.jsp" %>  

