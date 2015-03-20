<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ page isELIgnored="false"%>
<% 
	String titre = "PAGE 1";
	String droit = "${droit}"; 
%>

<jsp:include page="ihm/miseEnPage1.jsp">
    	<jsp:param name="titre" value="<%=titre%>"/>
	<jsp:param name="droit" value="<%=droit%>" />
</jsp:include>
	<p class="info">${message}</p>
	<c:if test="${ !erreur }">
		<p>
			Enregistré dans le bean : <c:out value="${donnee}"/><br/>
			Valeur1:<c:out value="${donnee.valeur1}"/><br/>
			Valeur2:<c:out value="${donnee.valeur2}"/>
		</p>
	 </c:if>
<jsp:include page="ihm/miseEnPage2.jsp" />
