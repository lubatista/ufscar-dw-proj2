<%-- 
    Document   : confirmaConsulta
    Created on : 12/04/2018, 11:17:30
    Author     : luanbatista
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda Médica</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" />
    </head>
    <body>
        <h1>Confirmação de Agendamento</h1>
        Confirme os dados da consulta antes de confirmar.
        <br/>
        Crm: ${sessionScope.novaConsulta.crm} <br/>
        Médico: ${sessionScope.novaConsulta.medico} <br/>
        Cpf: ${sessionScope.novaConsulta.cpf} <br/>
        Paciente: ${sessionScope.novaConsulta.paciente} <br/>
        Data: ${sessionScope.novaConsulta.data} <br/>
        <br/>
        <a href="GravarConsultaServlet">Confirmar</a>
        <a href="consultaForm.jsp">Modificar</a>
        <a href="index.jsp">Cancelar</a>
    </body>
</html>

