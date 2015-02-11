<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% 
	String titre = "PAGE 3";
	String droit = "${droit}"; 
%>


<jsp:include page="ihm/miseEnPage1.jsp">
    	<jsp:param name="titre" value="<%=titre%>"/>
	<jsp:param name="droit" value="<%=droit%>" />
</jsp:include>

Ceci constitue la page 3

Le nombre tiré aléatoirement était : ${nombre}
<br/>
valeur = <c:choose>
	 <c:when test="${nombre == 2}"><c:out value="${nombre}"/></c:when>
	 <c:when test="${nombre == 1}"><c:out value="${nombre}"/></c:when>
	 <c:otherwise>inconnue</c:otherwise>
</c:choose>

<jsp:include page="ihm/miseEnPage2.jsp" />
