<%@ page pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>

<div>

	<form method="post"  action="<c:url value="/creationCompte"/>">
	     
		<fieldset>
			<legend>Creation de compte</legend>
			<span class="erreur"><c:out value="${resultat}"/></span><br/>
			<label for ="nom">Nom<span class="requis">*</span></label>
			<input type="text" id ="nom" name="nom" size ="30" maxlength="30" />
			<span class="erreur"><c:out value ="${erreur['nom']}"/></span>
			<br />

            		<label for="mdp">Mot de Passe<span class="requis">*</span></label>
            		<input type="password" id="mdp" name="mdp" size="30" maxlength="30" />
            		<span class="erreur"><c:out value ="${erreur['mdp']}"/></span>
			<br />

			<label for="mdp">Email<span class="requis">*</span></label>
            		<input type="text" id="email" name="email" size="30" maxlength="30" />
            		<span class="erreur"><c:out value ="${erreur['email']}"/></span>
			<br />

		<input type="submit" value="Valider"  />
        	<input type="reset" value="Reset" /> <br />   
		</fieldset>
	</form>
</div>


