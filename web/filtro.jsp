<%-- 
    Document   : filtro
    Created on : 12/04/2018, 13:09:05
    Author     : luanbatista
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda Médica</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" />        
    </head>
    <body>
        <h1>Filtros</h1>
        <hr>
        <% if(request.getParameter("param").equalsIgnoreCase("medicoEspecialidade")) { %>
            Digite a especialidade:
            <br />
            <form action="ConsultaServlet" method="get">
                <input type="hidden" name="param" value="medicoEspecialidade" />
                <input type="text" name="value" value="" />
                <input type="submit" value="Buscar" />
            </form>
        <% } %>
        
        <% if(request.getParameter("param").equalsIgnoreCase("consultaPaciente")) { %>
            Digite o CPF do paciente:
            <br />
            <form action="ConsultaServlet" method="get">
                <input type="hidden" name="param" value="consultaPaciente" />
                <input type="text" name="value" value="" />
                <input type="submit" value="Buscar" />
            </form>
        <% } %>
        
        <% if(request.getParameter("param").equalsIgnoreCase("consultaMedico")) { %>
            Digite o CRM do médico:
            <br />
            <form action="ConsultaServlet" method="get">
                <input type="hidden" name="param" value="consultaMedico" />
                <input type="text" name="value" value="" />
                <input type="submit" value="Buscar" />
            </form>
        <% } %>
       
        
    </body>
</html>
