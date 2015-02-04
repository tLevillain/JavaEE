<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<div>
	<form method="get"  action="<c:url value="/creationDonnees"/>">
		<fieldset>
			<legend>Formulaire1</legend>
			<label for ="input1">Input1<span class="requis">*</span></label>
			<input type="text" id ="input1" name ="input1" value ="" size ="30" maxlength="30" />
			<br />

                    	<label for="input2">Input2</label>
                    	<input type="text" id="input2" name="input2" value="" size="30" maxlength="30" />
                    	<br />
		<input type="submit" value="Valider"  />
        <input type="reset" value="Reset" /> <br />
		</fieldset>
	</form>
</div>
