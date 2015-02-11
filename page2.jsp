<%@ page isELIgnored="false"%>
<% 
	String titre = "PAGE 2";
	String droit = "${droit}"; 
%>


<jsp:include page="ihm/miseEnPage1.jsp">
    	<jsp:param name="titre" value="<%=titre%>"/>
	<jsp:param name="droit" value="<%=droit%>" />
</jsp:include>

Ceci constitue la page 2

Message issu de la commande : ${message}
<jsp:include page="ihm/miseEnPage2.jsp" />
