<%@ page pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<script type="text/javascript">
function addInput(){
document.getElementById("ajout").innerHTML=document.getElementById("ajout").innerHTML + '<label for ="test">TestAjoutDynamique</label><input type="text" id="test" name = "test" size="30" maxlength="30" /></br>';
}
</script>

<div>
	<form method="post"  action="<c:url value="/controleur?cmd=creationDonnee"/>">
		<fieldset>
			<legend>Formulaire1</legend>
			<label for ="input1">Input1<span class="requis">*</span></label>
			<input type="text" id ="input1" name ="input1" value ="<c:out value="${donnee.valeur1}"/>" size ="30" maxlength="30" />
			<span class="erreur">${form.erreurs['input1']}</span>
			<br />

            <label for="input2">Input2<span class="requis">*</span></label>
            <input type="text" id="input2" name="input2" value="<c:out value="${donnee.valeur2}"/>" size="30" maxlength="30" />
            <span class="erreur">${form.erreurs['input2']}</span>
            <br />
	    <div id="ajout"></div>
		<input type="submit" value="Valider"  />
        <input type="reset" value="Reset" /> <br />   
	<input type="button" name="Ajouter un champ" value="Ajouter un champ" onClick="addInput()"/> 
		</fieldset>
	</form>
</div>


