<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalTime"%>
<%@page import="java.time.LocalDate"%>
<%@page import="com.hackaboss.pruebatecnica2.logica.Turno"%>
<%@page import="com.hackaboss.pruebatecnica2.logica.Ciudadano"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp" %> 
<%@include file="components/bodyinicial.jsp" %> 

<h2>Listado de Ciudadanos registrados</h2>
<p>A continuacion podra visualizar la lista completa de ciudadanos registrados</p>

<div class="card shadow mb-4">
    <div class="card-header py-3 row">
        <form action="SvBusquedaDni" method="post">
            <h6 class=" font-weight-bold mt-2 d-sm-inline-block">Búsqueda:</h6>
            <div class="col-sm-7 d-sm-inline-block">
                <input type="text" class="form-control form-control-user" name="dniBusqueda"
                       placeHolder="Ingrese un DNI">
            </div>
            <button type="submit" class="border-0 mt-1 d-sm-inline-block" >
                <img src="img\search.png">
            </button>
        </form>
        <form action="SvCiudadano" method="get" class="ml-5 pl-0 d-sm-inline-block">
            <div class="d-sm-inline-block">
                <button type="submit" class="border-0">
                    <h6 class="m-0 font-weight-bold mt-2 text-primary">Ver todos</h6>
                </button>

            </div>
        </form>
    </div>
    
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>DNI</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th style="width: 200px;" ></th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>DNI</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th></th>
                    </tr>
                </tfoot>
                <% List<Ciudadano> lista = (List<Ciudadano>) request.getSession().getAttribute("ciudadanos"); %>
                <tbody>
                    <% for(Ciudadano ciud : lista){ %>
                    <tr>
                        <td><%=ciud.getDni() %></td>
                        <td><%=ciud.getNombre() %></td>
                        <td><%=ciud.getApellido() %></td>
                        
                        <td style="display:flex;" > 
                            <form name="eliminar" action="SvElimCiudadano" method="post" class="ml-3 mr-3">
                                <button type="submit" class="btn btn-danger btn-user btn-block">
                                    <i class="fas fa-trash-alt"></i> 
                                </button>
                                <input type="hidden" name="id" value="<%=ciud.getId() %>">
                            </form> 
                            <form name="editar" action="SvModifCiudadano" method="get">
                                <button type="submit" class="btn btn-primary btn-user btn-block">
                                    <i class="fas fa-pencil-alt"></i> 
                                </button>
                                <input type="hidden" name="id" value="<%=ciud.getId() %>">
                            </form>
                            
                        </td>
                    </tr>
                    <% } %> 

                </tbody>
            </table>
        </div>
    </div>
</div>
<%@include file="components/footer.jsp" %>            
<%@include file="components/bodyfinal.jsp" %> 
