package com;

import java.util.HashMap;
import java.util.Map;
import java.io.*;

import javax.servlet.http.HttpServletRequest;

public class CreationTexte implements Commande {
    private static final String CHAMP_TEXTE    ="mybox";
    private static final String CHAMP_NOM      ="nomFichier";

    private String              resultat;
    private Map<String, String> erreurs         = new HashMap<String, String>();

    private String next;

    public CreationTexte(String next){
	this.next = next;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public String execute(HttpServletRequest req) throws Exception 
    {
	String texte = creerXML(req);
	req.setAttribute("xml", texte);
	req.setAttribute("nom", getValeurChamp(req, CHAMP_NOM));
	return next;
    }

    public String creerXML( HttpServletRequest request ) throws IOException {
	String texte = getValeurChamp ( request, CHAMP_TEXTE );
	String nom = getValeurChamp ( request, CHAMP_NOM );
	PrintWriter out = new PrintWriter("/home/syron/Documents/" + nom + ".xml");
	out.println(texte);
	out.close();
	return texte;
    }

    private String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}