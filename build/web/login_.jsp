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
        <h1>Acesso ao Sistema</h1>
        <hr>
            Digite seu usuário e senha:
            <br />
            <form action="LoginServlet" method="post">
                <input type="text" name="user" value="" />
                <input type="password" name="pass" value="" />
                <input type="submit" value="Acessar" />
            </form>
        <hr>
        <br />
        Para médicos digite o CRM e a senha
        <br />
        Para paciente digite o CPF e a senha
        <br />
        Para o adminstrador digite o usuário e senha
        
    </body>
</html>
