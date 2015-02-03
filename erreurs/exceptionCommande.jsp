<!--
	JSP permettant d'afficher un message d'erreur suite &agrave; un
	plantage lors de l'execution d'une commande.
	NB : ne sert que pour le debuggage de l'application.
-->

<html>
<body>
	<h1>
		ERREUR ! <br/>
		Une exception est survenue !
	</h1>
<h2>d&eacute;tail de l'exception :</h2>
<table border="1">
<tr><th>Nom de la commande</th><td><%=request.getParameter("cmd")%></td></tr>
<tr><th>Classe de la commande</th><td><%=request.getAttribute("classe")%></td></tr>
<tr><th>Classe d'exception</th><td><%=request.getAttribute("classeException")%></td></tr>
<tr><th>Message d'exception</th><td><%=request.getAttribute("exception")%></td></tr>
</table>

</body>
</html>
