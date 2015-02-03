<% 
	String titre = "PAGE 1";
	String droit = "admin"; 
%>


<jsp:include page="ihm/miseEnPage1.jsp">
    	<jsp:param name="titre" value="<%=titre%>"/>
	<jsp:param name="droit" value="<%=droit%>" />
</jsp:include>

Ceci constitue la page 1

<jsp:include page="ihm/miseEnPage2.jsp" />
