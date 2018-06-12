<%-- 
    Document   : pacienteForm
    Created on : 12/04/2018, 08:32:27
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
        <h1>Novo Médico</h1>
        <hr>
        <c:if test="${!empty requestScope.mensagens}">
            <ul class="erro">
            <c:forEach items="${requestScope.mensagens}" var="mensagem">
                <li>${mensagem}</li>
            </c:forEach>
            </ul>
            <hr>
        </c:if>

        <form action="GravarMedicoServlet" method="post">
            Dados do Médico:<br/>
            CRM: <input name="crm" type="text" value="${sessionScope.novoMedico.crm}" /><br/>
            Nome: <input name="nome" type="text" value="${sessionScope.novoMedico.nome}" /><br/>
            Especialidade: <input name="especialidade" type="text" value="${sessionScope.novoMedico.especialidade}" /><br/>
            Senha: <input name="senha" type="text" value="${sessionScope.novoMedico.senha}" /><br/>
            <input type="submit" value="Enviar"/>
            <a href="index.jsp">Cancelar</a>
        </form>
    </body>
</html>