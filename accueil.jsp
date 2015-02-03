<% 
	String titre = "ACCUEIL ADMINISTRATION";
	String droit = "admin"; 
%>


<jsp:include page="ihm/miseEnPage1.jsp">
    	<jsp:param name="titre" value="<%=titre%>"/>
	<jsp:param name="droit" value="<%=droit%>" />
</jsp:include>

BIENVENUE !

<jsp:include page="ihm/miseEnPage2.jsp" />
