<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% 
	String titre = "ACCUEIL";
	String droit = "${droit}"; 
%>


<jsp:include page="ihm/miseEnPage1.jsp">
    	<jsp:param name="titre" value="<%=titre%>"/>
	<jsp:param name="droit" value="<%=droit%>" />
</jsp:include>
<p>Bienvenue ${sessionScope.sessionUtilisateur.nom}.</p>
<p>Bienvenue sur le site construit à l'aide du javaEE <strong>E.S.M.A</strong>, site en cour d'amélioration.</p>

<jsp:include page="ihm/miseEnPage2.jsp" />
