<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.hackaboss.pruebatecnica2.logica.Turno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp" %> 

<%@include file="components/bodyinicial.jsp" %> 

<div>
    <h4 class="row">Atención del día</h4>
</div>

<div class="row">
    <% List<Long> totalesHoy = (List<Long>) request.getSession().getAttribute("totalesHoy");
        Long porcPendientesHoy = 0L, porcAtendidosHoy = 0L;
    if(totalesHoy != null){  
        if(totalesHoy.get(0)==0){
            porcPendientesHoy = 0L;
            porcAtendidosHoy = 0L;
        } else {
            porcPendientesHoy = (totalesHoy.get(1) * 100) / totalesHoy.get(0);
            porcAtendidosHoy = (totalesHoy.get(2)*100)/totalesHoy.get(0);
        } %>
    
    <div class="col-md-4 mb-4">
        <div class="card border-left-primary shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-lg-center font-weight-bold text-primary text-uppercase mb-1"
                             style="font-size: 20px;">
                            Total Turnos</div>
                        <div class="h5 mb-0 font-weight-bold text-gray-800">
                            <%=totalesHoy.get(0)%>
                        </div>
                    </div>
                    <div class="col-auto">
                        <i class="fas fa-calendar fa-2x text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-4 mb-4">
        <div class="card border-left-info shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-lg-center font-weight-bold text-info text-uppercase mb-1"
                             style="font-size: 20px;">
                            Turnos Pendientes
                        </div>
                        <div class="row no-gutters align-items-center">
                            <div class="col-auto">
                                <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">
                                    <%=totalesHoy.get(1)%>
                                </div>
                            </div>
                            <div class="col">
                                <div class="progress progress-sm mr-2">
                                    <div class="progress-bar bg-info" role="progressbar"
                                         style="width: <%=porcPendientesHoy%>%" aria-valuenow="50" aria-valuemin="0"
                                         aria-valuemax="100"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-auto">
                        <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
   
    <div class="col-md-4 mb-4">
        <div class="card border-left-warning shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="text-lg-center font-weight-bold text-warning text-uppercase mb-1"
                             style="font-size: 20px;">
                            Turnos Atendidos
                        </div>
                        <div class="row no-gutters align-items-center">
                            <div class="col-auto">
                                <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">
                                    <%=totalesHoy.get(2)%>
                                </div>
                            </div>
                            <div class="col">
                                <div class="progress progress-sm mr-2">
                                    <div class="progress-bar bg-warning" role="progressbar"
                                         style="width: <%=porcAtendidosHoy%>%" aria-valuenow="50" aria-valuemin="0"
                                         aria-valuemax="100"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-auto">
                        <i class="fas fa-comments fa-2x text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <% } %>
</div>

<div>
    <h4 class="row">Atención General</h4>
</div>
<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseEstadisticas"
   aria-expanded="true" aria-controls="collapseEstadisticas">
    <i class="fas fa-regular fa-chart-bar"></i>
    <span>Ver Estadísticas</span>
</a>
<div id="collapseEstadisticas" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
    <div class="row">
        <% List<Long> totales = (List<Long>) request.getSession().getAttribute("totales");
        Long porcPendientes = 0L, porcAtendidos=0L;
        if(totales != null){  
            if(totales.get(0)==0){
                porcPendientes = 0L;
                porcAtendidos = 0L;
            } else {
                porcPendientes = (totales.get(1) * 100) / totales.get(0);
                porcAtendidos = (totales.get(2)*100)/totales.get(0);
            }
        } else {
            totales.set(0, 0L);
            totales.set(1, 0L);
            totales.set(2, 0L);
            porcPendientes = 0L;
            porcAtendidos = 0L; } %>
        
        <div class="col-md-4 mb-4">
            <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-lg-center font-weight-bold text-primary text-uppercase mb-1">
                                Total Turnos</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">
                                <%=totales.get(0) %>
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-calendar fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4 mb-4">
            <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-lg-center font-weight-bold text-info text-uppercase mb-1">
                                Turnos Pendientes
                            </div>
                            <div class="row no-gutters align-items-center">
                                <div class="col-auto">
                                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">
                                        <%=totales.get(1)%>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="progress progress-sm mr-2">
                                        <div class="progress-bar bg-info" role="progressbar"
                                             style="width: <%=porcPendientes%>%" aria-valuenow="50" aria-valuemin="0"
                                             aria-valuemax="100"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="col-md-4 mb-4">
            <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-lg-center font-weight-bold text-warning text-uppercase mb-1">
                                Turnos Atendidos
                            </div>
                            <div class="row no-gutters align-items-center">
                                <div class="col-auto">
                                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">
                                        <%=totales.get(2)%>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="progress progress-sm mr-2">
                                        <div class="progress-bar bg-warning" role="progressbar"
                                             style="width: <%=porcAtendidos%>%" aria-valuenow="50" aria-valuemin="0"
                                             aria-valuemax="100"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-comments fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    </div>
    <% List<Long> listaTramites = (List)request.getSession().getAttribute("listaTramites"); 
        if(listaTramites == null){
            listaTramites.set(0, 0L);
            listaTramites.set(1, 0L);
            listaTramites.set(2, 0L);
            listaTramites.set(3, 0L);
        } %>
    <div class="row">
    <div class="col-xl-3 col-md-6 mb-4"">
        <div class="card border-primary">
            <div class="text-lg-center font-weight-bold text-primary text-uppercase mb-1">
                Total turnos
            </div>
            <div class="text-lg-center font-weight-bold text-primary text-uppercase mb-1">
                                Consulta/Información
            </div>
            <div class="text-lg-center font-weight-bold text-gray-800 text-uppercase mb-1">
                <%=listaTramites.get(0) %>
            </div>
        </div>
    </div>
    <div class="col-xl-3 col-md-6 mb-4"">
        <div class="card border-success">
            <div class="text-lg-center font-weight-bold text-success text-uppercase mb-1">
                Total turnos
            </div>
            <div class="text-lg-center font-weight-bold text-success text-uppercase mb-1">
                                Atencion Comercial
            </div>
            <div class="text-lg-center font-weight-bold text-gray-800 text-uppercase mb-1">
                        <%=listaTramites.get(1) %>       
            </div>
        </div>
    </div>
    <div class="col-xl-3 col-md-6 mb-4"">
        <div class="card border-info">
            <div class="text-lg-center font-weight-bold text-info text-uppercase mb-1">
                Total turnos
            </div>
            <div class="text-lg-center font-weight-bold text-info text-uppercase mb-1">
                                Atencion Tesoreria
            </div>
            <div class="text-lg-center font-weight-bold text-gray-800 text-uppercase mb-1">
                      <%=listaTramites.get(2) %>         
            </div>
        </div>
    </div>
    <div class="col-xl-3 col-md-6 mb-4"">
        <div class="card border-warning">
            <div class="text-lg-center font-weight-bold text-warning text-uppercase mb-1">
                Total turnos
            </div>
            <div class="text-lg-center font-weight-bold text-warning text-uppercase mb-1">
                                Reclamos
            </div>
            <div class="text-lg-center font-weight-bold text-gray-800 text-uppercase mb-1">
                         <%=listaTramites.get(3) %>       
            </div>
        </div>
    </div>
</div>                                         
                                            
</div>          


<hr>
<div class="row">
    
    <div class="row">
        <div class="col-lg-6 mb-3">
            <a class="nav-link" href="SvTurnosPendientes">
                <div class="card bg-info text-white shadow">
                    <div class="card-body text-lg-center" style="font-size: 20px;">
                        Ver Turnos Pendientes
                    </div>
                </div>
            </a>
        </div>

        <div class="col-lg-6 mb-3">
            <a class=" nav-link" href="SvProximoTurno">
                <div class="card bg-warning text-white shadow">
                    <div class="card-body text-md-center" style="font-size: 20px;">
                        Atender próximo turno

                    </div>
                </div>
            </a>
        </div>

        <div class="col-lg-6 mb-3">
            <a class="nav-link" href="SvTurno">
                <div class="card bg-primary text-white shadow">
                    <div class="card-body text-lg-center" style="font-size: 20px;">
                        Ver Listado de Turnos
                    </div>
                </div>
            </a>
        </div>

        <div class="col-lg-6 mb-3">
            <a class="nav-link" href="SvCiudadano">
                <div class="card bg-primary text-white shadow">
                    <div class="card-body text-md-center" style="font-size: 20px;">
                        Ver Listado de Ciudadanos
                    </div>
                </div>
            </a>
        </div>
        <div class="col-lg-6 mb-3">
            <a class="nav-link" href="solicitarTurno.jsp">
                <div class="card bg-primary text-white shadow">
                    <div class="card-body text-lg-center" style="font-size: 20px;">
                        Solicitar Nuevo Turno
                    </div>
                </div>
            </a>
        </div>

        <div class="col-lg-6 mb-3">
            <a class="nav-link" href="registrarCiudadano.jsp">
                <div class="card bg-primary text-white shadow">
                    <div class="card-body text-md-center" style="font-size: 20px;">
                        Registrar Nuevo Ciudadano
                    </div>
                </div>
            </a>
        </div>


    </div>
</div>


<%@include file="components/footer.jsp" %>            
<%@include file="components/bodyfinal.jsp" %>             
