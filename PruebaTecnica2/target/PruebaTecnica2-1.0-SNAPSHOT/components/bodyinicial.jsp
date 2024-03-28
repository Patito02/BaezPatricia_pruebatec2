<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body id="page-top">

    <div id="wrapper">

        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="SvInicio">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-regular fa-calendar"></i>
                </div>
                <div class="sidebar-brand-text mx-3">GESTIÓN DE TURNOS</sup></div>
            </a>

            <hr class="sidebar-divider my-0">

            <li class="nav-item active">
                <a class="nav-link" href="SvInicio">
                    <i class="fas fa-solid fa-bars"></i>
                    <span>Sistema de Turnos </span></a>
            </li>

            <hr class="sidebar-divider">

            <div class="sidebar-heading">
                Sistema
            </div>

            
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTurnos"
                    aria-expanded="true" aria-controls="collapseTurnos">
                    <i class="fas fa-regular fa-calendar"></i>
                    <span> Turnos</span>
                </a>
                <div id="collapseTurnos" class="collapse" aria-labelledby="headingTurnos" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Acciones:</h6>
                        <a class="collapse-item" href="SvTurno"> Ver turnos</a>
                        <a class="collapse-item" href="solicitarTurno.jsp"> Nuevo Turno</a>
                    </div>
                </div>
            </li>
            
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePendientes"
                    aria-expanded="true" aria-controls="collapsePendientes">
                    <i class="fas fa-solid fa-clipboard-list"></i>
                    <span> Turnos Pendientes</span>
                </a>
                <div id="collapsePendientes" class="collapse" aria-labelledby="headingPendientes"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Acciones:</h6>
                        <a class="collapse-item" href="SvTurnosPendientes"> Ver turnos pendientes</a>
                        <a class="collapse-item" href="SvProximoTurno"> Atender próximo turno</a>
                    </div>
                </div>
            </li>
            
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseCiudadanos"
                    aria-expanded="true" aria-controls="collapseCiudadanos">
                    <i class="fas fa-solid fa-user"></i>
                    <span> Ciudadanos</span>
                </a>
                <div id="collapseCiudadanos" class="collapse" aria-labelledby="headingCiudadanos"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Acciones:</h6>
                        <a class="collapse-item" href="SvCiudadano"> Ver ciudadanos</a>
                        <a class="collapse-item" href="registrarCiudadano.jsp"> Registrar ciudadano</a>
                        
                    </div>
                </div>
            </li>
            
            <hr class="sidebar-divider">
        </ul>

        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                    
                    <img src="img\logo.png"/>
                    <div class="d-sm-flex align-items-center justify-content-between mb-4 mt-4">
                        <h1 class="h3 mb-0 ml-2 text-gray-800"> SISTEMA DE GESTION DE TURNOS</h1>
                    </div>
                </nav>

                
                <div class="container-fluid">
