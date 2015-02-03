<!--
	JSP permettant d'afficher un message d'erreur suite &agrave; une demande 
	d'ex&eacute;cution par le controleur d'une commande inexistante.
	NB : ne sert que pour le debuggage de l'application.
-->

<html>
<body>
	<h1>
		ERREUR ! <br/>
		La commande <i><%= request.getParameter("cmd") %></i> n'existe pas dans la Hashmap du controleur !
	</h1>
</body>
</html>