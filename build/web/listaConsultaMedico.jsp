<%-- 
    Document   : listaMedicoTodos
    Created on : 12/04/2018, 12:44:53
    Author     : luanbatista
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda Médicia</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" />
    </head>
    <body>
        <h1>Lista de Consulta Por Médico</h1>
        <hr>
        <c:if test="${empty requestScope.todasConsultas}">
            Não há consultas para o médico selecionado!
        </c:if>
        <c:if test="${!empty requestScope.todasConsultas}">
            <table>
                <tr>
                    <th class="esquerda">CRM</th>
                    <th>Médico</th>
                    <th>CPF</th>
                    <th>Paciente</th>
                    <th>Data</th>
                </tr>
                <c:forEach items="${requestScope.todasConsultas}" var="consulta">
                    <tr>
                        <td class="esquerda">${consulta.crm}</td>
                        <td>${consulta.medico}</td>
                        <td>${consulta.cpf}</td>
                        <td>${consulta.paciente}</td>
                        <td>${consulta.data}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>