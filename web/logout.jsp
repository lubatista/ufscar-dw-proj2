<%-- 
    Document   : filtro
    Created on : 12/04/2018, 13:09:05
    Author     : luanbatista
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:remove scope="session" var="loginUser" />
<c:remove scope="session" var="loginType" />
<c:remove scope="session" var="loginDoc" />

<% response.sendRedirect("login.jsp"); %>
