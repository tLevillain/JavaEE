<%@ page pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ page isELIgnored="false"%>
<% 
	String titre = "Connexion";
	String droit = "${droit}"; 
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<%@ page contentType="text/html;charset=UTF-8" %>
<title>Connexion</title>
<link rel="stylesheet" type="text/css" href="ihm/site.css">
<link rel="stylesheet" type="text/css" href="ihm/formulaire.css">
</head>
<body>

<div class="haut">
     <div class="hautGauche">
     	  <img src="ihm/images/LogoGreyc.png" alt="logo webapp" width="141" height="47"/>
     </div>
     <div class="hautCentre">
     	  	<strong>E.S.M.A</strong>
     </div>
     <div class="hautCentreBis">
     		Editeur de Systèmes Multi-Agents
     </div>
</div>

<div class="milieu">
     <div class="menu">
	  <br/>
	  <br/>
	  <hr/>
     	  <a href="/Stage/connexion">Connexion</a>
	  <hr/>
	  <br/>
	  <hr/>
	  Creation compte
	  <hr/>
     </div>
     <div class="contenu">

<jsp:include page="ihm/formulaire3.jsp" />
