<!--
	JSP permettant d'afficher un message d'erreur suite à un problème
	pendant la phase d'initialisation (méthode init) du controleur
-->

<html>
<body>
	<% 
		String mess = (String) request.getAttribute("mess");
	%>
	<h1>
		ERREUR <br/>
	</h1>
	Une erreur s'est produite pendant l'exécution de la méthode <it>init</it> de la servlet controleur. <br/>
	<%= mess %>
</body>
</html>
