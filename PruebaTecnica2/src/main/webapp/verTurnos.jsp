
<%@page import="com.hackaboss.pruebatecnica2.logica.Turno"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp" %> 
<%@include file="components/bodyinicial.jsp" %> 

<h2>Listado de Turnos</h2>
<p>A continuacion podra visualizar la lista completa de turnos, filtrar por fecha o estado:</p>

<div class="card shadow mb-4">
    <div class="card-header py-3 row">

        <form action="SvBusquedaFecha" method="get" class="col-sm-8 pr-0">
            <h6 class="m-0 font-weight-bold mb-2 mr-2 d-sm-inline-block">Búsqueda:</h6>
            
            <div class="col-sm-4 d-sm-inline-block">
                <input type="date" class="form-control form-control-user" name="fechaBusqueda" required>
            </div>
            
            <div class="col-sm-4 d-sm-inline-block">
                <select name="selectEstado" class="form-control form-control-user d-sm-inline-block">
                    <option value="0" selected >Seleccione un Estado</option>
                    <option value="1" >En espera</option>
                    <option value="2">Ya atendido</option>
                </select>
            </div>
            <button type="submit" class="border-0 mt-1 d-sm-inline-block" >
                <img src="img\search.png">
            </button>
        </form>

        <form action="SvTurno" method="get" class="ml-0 pl-0 d-sm-inline-block">
            <div >
                <button type="submit" class="border-0">
                    <h6 class="m-0 font-weight-bold mt-2  text-primary">Ver todos</h6>
                </button>

            </div>
        </form>


    </div>

    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                    <tr>
                        <th>Fecha</th>
                        <th>Hora</th>
                        <th>Trámite</th>
                        <th>Estado</th>
                        <th>DNI</th>
                        <th>Ciudadano</th>
                        <th ></th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>Fecha</th>
                        <th>Hora</th>
                        <th>Trámite</th>
                        <th>Estado</th>
                        <th>DNI</th>
                        <th>Ciudadano</th>
                        <th></th>
                    </tr>
                </tfoot>
                <% List<Turno> lista = (List<Turno>) request.getSession().getAttribute("turnos");
                    String estado = ""; 
                    String tramite = "";%>
                <tbody>
                    <% for (Turno turn : lista) {%>
                    <tr>
                        <td><%=turn.getFecha()%></td>
                        <td><%=turn.getHora()%></td>
                        <%if(turn.getTramite()==1){ tramite="Consulta / Información";
                            } else if(turn.getTramite()==2){tramite="Atención Comercial"; 
                            } else if(turn.getTramite()==3){tramite="Atención Tesorería";
                            } else if(turn.getTramite()==4){tramite="Reclamos";}; %>
                        <td><%=tramite %></td>
                        <%if(turn.getEstado()==1){ estado="En Espera";
                            } else if(turn.getEstado()==2){estado="Ya atendido"; }; %>
                        <td><%=estado %></td>
                        <td><%=turn.getCiudadano().getDni()%></td>
                        <% String nombreusu = turn.getCiudadano().getApellido() + " "
                                    + turn.getCiudadano().getNombre();%>
                        <td><%=nombreusu%></td>
                        <td style="display:flex;">
                            <form name="eliminar" action="SvElimTurno" method="post" class="ml-3 mr-3">
                                <button type="submit" class="btn btn-danger btn-user btn-block center">
                                    <i class="fas fa-solid fa-ban"></i>
                                </button>
                                <input type="hidden" name="id" value="<%=turn.getId()%>">
                            </form>
                            <form name="editar" action="SvModifTurno" method="get" >
                                <button type="submit" class="btn btn-primary btn-user btn-block center">
                                    <i class="fas fa-pencil-alt"></i>
                                </button>
                                <input type="hidden" name="id" value="<%=turn.getId()%>">
                            </form>
                        </td>
                    </tr>
                    <% }%> 

                </tbody>
            </table>
        </div>
    </div>
</div>
<%@include file="components/footer.jsp" %>            
<%@include file="components/bodyfinal.jsp" %> 