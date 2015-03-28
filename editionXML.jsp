<%@ page pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ page isELIgnored="false"%>
<% 
	String titre = "Edition/Creation de document XML";
	String droit = "${droit}"; 
%>


<jsp:include page="ihm/miseEnPage1.jsp">
    	<jsp:param name="titre" value="<%=titre%>"/>
	<jsp:param name="droit" value="<%=droit%>" />
</jsp:include>

<%--<jsp:include page="ihm/formulaire1.jsp" />--%>
<jsp:include page="ihm/formulaire2.jsp" />

<jsp:include page="ihm/miseEnPage2.jsp" />
