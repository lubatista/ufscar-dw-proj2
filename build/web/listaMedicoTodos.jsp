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
        <c:if test="${empty requestScope.listaMedicos}">
            Não há consultas para o médico selecionado!
        </c:if>
        <c:if test="${!empty requestScope.listaMedicos}">
            <table>
                <tr>
                    <th class="esquerda">CRM</th>
                    <th>Médico</th>
                    <th>Especialidade</th>
                </tr>
                <c:forEach items="${requestScope.listaMedicos}" var="medico">
                    <tr>
                        <td class="esquerda">${medico.crm}</td>
                        <td>${medico.nome}</td>
                        <td>${medico.especialidade}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>