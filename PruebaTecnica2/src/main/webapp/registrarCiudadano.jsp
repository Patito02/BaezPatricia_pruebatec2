<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp" %> 
<%@include file="components/bodyinicial.jsp" %> 

<h2>Registrar Ciudadanos</h2>

<form class="user" action="SvCiudadano" method="POST">
    <p>Ingrese los datos de la persona: </p>
    <div class="form-group row">
        <div class="col-sm-6 mb-3 mb-sm-0">
            <input type="text" class="form-control form-control-user" name="nombre"
                   placeholder="Nombre" required>
        </div>
        
    </div>
    <div class="form-group row">
        <div class="col-sm-6">
            <input type="text" class="form-control form-control-user" name="apellido"
                   placeholder="Apellido" required>
        </div>
    </div> 
    <div class="form-group row">
        <div class="col-sm-6">
            <input type="text" class="form-control form-control-user uppercase" name="dni"
                   placeholder="DNI" style="text-transform: uppercase" required>
        </div>
    </div> 
    
    <button type="submit" class="btn btn-primary btn-user btn-block col-sm-6 mb-3">
        Registrar Ciudadano
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
