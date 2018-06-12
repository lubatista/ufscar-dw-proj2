<%-- 
    Document   : pacienteForm
    Created on : 12/04/2018, 08:32:27
    Author     : luanbatista
--%>

<%-- 
    Document   : palpiteForm
    Created on : 05/04/2018, 08:52:56
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
        <h1>Novo Paciente</h1>
        <hr>
        <c:if test="${!empty requestScope.mensagens}">
            <ul class="erro">
            <c:forEach items="${requestScope.mensagens}" var="mensagem">
                <li>${mensagem}</li>
            </c:forEach>
            </ul>
            <hr>
        </c:if>

        <form action="GravarPacienteServlet" method="post">
            Dados do Paciente:<br/>
            CPF: <input name="cpf" type="text" value="${sessionScope.novoPaciente.cpf}" /><br/>
            Nome: <input name="nome" type="text" value="${sessionScope.novoPaciente.nome}" /><br/>
            Telefone: <input name="telefone" type="text" value="${sessionScope.novoPaciente.telefone}" /><br/>
            Data de nascimento: <input name="datanasc" type="text" value="${sessionScope.novoPaciente.datanasc}" /><br/>
            Sexo: <input name="sexo" type="radio" value="Masculino" /> Masculino
                  <input name="sexo" type="radio" value="Feminino" /> Feminino <br />
            Senha: <input name="senha" type="text" value="${sessionScope.novoPaciente.senha}" /><br/>
            <input type="submit" value="Enviar"/>
            <a href="index.jsp">Cancelar</a>
        </form>
    </body>
</html>