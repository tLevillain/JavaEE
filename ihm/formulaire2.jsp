<%@ page pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<script type="text/javascript">
function insertTab(o, e) 
{
	var kC = e.keyCode ? e.keyCode : e.charCode ? e.charCode : e.which;
	if(kC == 9 && !e.shiftKey && !e.ctrlKey && !e.altKey)
	{
		var oS = o.scrollTop;
		if(o.setSelectionRange)
		{
			var sS = o.selectionStart;
			var sE = o.selectionEnd;
			o.value = o.value.substring(0, sS) + "\t" + o.value.substr(sE);
			o.setSelectionRange(sS + 1, sS + 1);
			o.focus();
		}
		else if (o.createTextRange)
		{
			document.selection.createRange().text = "\t";
			e.returnValue = false;
		}
		o.scrollTop = oS;
		if(e.preventDefault) 
		{
			e.preventDefault();
		}
		return false;
	}
	return true;
}
</script>

<div>
	<form method="post"  action="<c:url value="/controleur?cmd=creationTexte"/>">
		<fieldset>
			<legend>Creation avec texte</legend>
			<label for = "nomFichier">Nom  <span class="requis">*</span></label>
			<c:out value ="${erreur['nomFichier']}"/><br/>
			<input type = "text" name = "nomFichier" id = "nomFichier" size = "30" maxlength = "30"  value="<c:out value ="${nom}"/>" /><br/><br/>
			
			<label for ="input1">Contenu XML  <span class="requis">*</span></label><br/><br/>
			<c:out value ="${erreur['mybox']}"/><c:out value ="${resultat}"/><br/>
			<textarea id ="text-area" name = "mybox" onkeydown="insertTab(this,event);" row="100" cols="100" style="min-height:300px;"><c:out value ="${xml}"/>		
			</textarea><br/>	
		<input type="submit" value="Valider"  />
        <input type="reset" value="Reset" /> <br />   
	       
		</fieldset>
	</form>
</div>


