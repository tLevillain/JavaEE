<%@ page pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<script type="text/javascript">

</script>

<div>
	<form method="post"  action="<c:url value="/controleur?cmd=creationTexte"/>">
		<fieldset>
			<legend>Creation avec texte</legend>
			<label for ="input1">Saisissez votre fichier XML ici<span class="requis">*</span></label>
			<textarea id ="text-area" name = "mybox" row="100" cols="100"></textarea><br/>	
		<input type="submit" value="Valider"  />
        <input type="reset" value="Reset" /> <br />   
	 
		</fieldset>
	</form>
</div>


