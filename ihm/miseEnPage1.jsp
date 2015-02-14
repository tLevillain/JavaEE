<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">
<%@ page contentType="text/html;charset=UTF-8" %>
<title><%= request.getParameter("titre")%></title>
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

<%
	String typeConnexion = request.getParameter("droit");
	/*
		Le droit de connexion doit etre consult ou admin. Si 
		ce n'est aucun des deux alors le droit retenu sera consult.
	 */
	boolean afficheMenuAdmin; 
	
	if (typeConnexion == null) {
		afficheMenuAdmin = false;
	}
	else {
		afficheMenuAdmin = typeConnexion.equals("admin");
	}
%>

<div class="milieu">
     <div class="menu">
	  <a href="controleur?cmd=deconnect"> D&eacute;connexion </a>
	  <br/>
	  <br/>
	  <hr/>
     	  Consultation
	  <hr/>
	  <ul>
	  	<li><a href="controleur">Acceuil</a></li>
	    <li><a href="controleur?cmd=page1">Nouveau</a></li>
	    <li><a href="controleur?cmd=page2">Edition</a></li>
	  </ul>
	  <%
		if (afficheMenuAdmin) {
	  %>
	  <br/>
	  <hr/>
	  Administration
	  <hr/>
	  <ul>
	    <li><a href="controleur?cmd=page3">Administration</a></li>
	    <li><a href=""> </a></li>
	  </ul>
	  <%
		}
	  %>
	  
     </div>
     <div class="contenu">
       <div id="titre">
	 <%= request.getParameter("titre")%>
       </div>
