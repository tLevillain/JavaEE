
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<% 
	String titre = "PAGE 1";
	String droit = "admin"; 
%>

<jsp:include page="ihm/miseEnPage1.jsp">
    	<jsp:param name="titre" value="<%=titre%>"/>
	<jsp:param name="droit" value="<%=droit%>" />
</jsp:include>
<jsp:include page="ihm/formulaire1.jsp" />
<jsp:include page="ihm/miseEnPage2.jsp" />
