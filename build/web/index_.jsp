<%-- 
    Document   : index
    Created on : 12/04/2018, 09:22:58
    Author     : luanbatista
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:remove scope="session" var="novoPaciente" />
<c:remove scope="session" var="novoMedico" />
<c:remove scope="session" var="novaConsulta" />

<c:if test="${empty sessionScope.loginType}">
    <% response.sendRedirect("login.jsp"); %>
</c:if>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda Médica</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" />        
    </head>
    <body>
        <h1>Sistema de Agendamento Médico</h1>
        <hr>
        <c:if test="${sessionScope.loginType == 'admin'}">
            Administrador: ${sessionScope.loginUser} - <a href="logout.jsp">Sair</a>
        </c:if>
        <c:if test="${sessionScope.loginType == 'medico'}">
            Médico: ${sessionScope.loginUser} - <a href="logout.jsp">Sair</a>
        </c:if>
        <c:if test="${sessionScope.loginType == 'paciente'}">
            Paciente: ${sessionScope.loginUser} - <a href="logout.jsp">Sair</a>
        </c:if>
        <hr>
        <c:if test="${!empty mensagem}">
            ${mensagem}
            <hr>
        </c:if>
        <p>Opçõe do sistema<p>
        <p>Escolha o que deseja fazer:</p>
        
        <c:if test="${sessionScope.loginType ==  'admin'}">
            <a href="medicoForm.jsp"  >Cadastrar Médico</a><br/>
            <a href="pacienteForm.jsp">Cadastrar Paciente</a><br/>
        </c:if>
            
        <c:if test="${sessionScope.loginType ==  'paciente'}">
            <a href="consultaForm.jsp">Agendar Consulta</a><br/>
            <a href="ListaConsultaPacienteServlet">Listar Constultas Paciente</a><br/>
        </c:if>
            
        <c:if test="${sessionScope.loginType ==  'medico'}">
            <a href="ListaConsultaMedicoServlet">Listar Constultas Médico</a><br/>
        </c:if>
        
        <a href="filtro.jsp?param=medicoEspecialidade">Listar todos os médicos por especialidade</a><br/>
        <a href="ConsultaServlet?param=todosMedico">Listar todos os médicos</a><br/>
       
    </body>
</html>