package com;

import java.util.HashMap;
import java.util.Map;
import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.*;


import java.io.*;
import org.jdom2.*;
import org.jdom2.output.*;
import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;


public class CreationTexte implements Commande {
    private static final String CHAMP_TEXTE    ="mybox";
    private static final String CHAMP_NOM      ="nomFichier";

    private String              resultat;
    private Map<String, String> erreurs;

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
	erreurs = new HashMap<String, String>();
	resultat = "";
	String texte = getValeurChamp(req, CHAMP_TEXTE).trim();
	String nom = getValeurChamp(req, CHAMP_NOM);
	
	try {
	    validationNom(nom);
	} catch(Exception e) {
	    setErreur(CHAMP_NOM, e.getMessage());
	}


	nom = nom + ".xml";
	try {
	    validationTexte(texte);
	    creerXML(texte, nom);
	} catch(Exception e) {
	    setErreur(CHAMP_TEXTE, e.getMessage());
	}

	if(erreurs.isEmpty()) {
	    resultat = "Document " + nom + " créé avec succés.";
	} else {
	    resultat ="";
	}
	req.setAttribute("resultat", resultat);
	req.setAttribute("erreur", erreurs);
	req.setAttribute("xml", texte);
	req.setAttribute("nom", getValeurChamp(req, CHAMP_NOM));
	return next;
    }

    private void creerXML(String texte, String nom) throws IOException, Exception {
	PrintWriter out = new PrintWriter("/home/syron/Documents/" + nom);
	out.println(texte);
	out.close();
	SAXBuilder builder = new SAXBuilder(XMLReaders.DTDVALIDATING);
        Document doc = builder.build(new File("/home/syron/Documents/" + nom));
    }

    private String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

    private void validationTexte(String texte) throws Exception {
	if(texte==null || texte.trim() == "") {
	    throw new Exception("Merci d'entrer une valeur");
	}
    }

    private void validationNom(String nom) throws Exception {
	if(nom==null || nom.trim()== "") {
	    throw new Exception("Merci d'entrer une valeur");
	}
    }

    private void setErreur(String champ, String message) {
	erreurs.put(champ, message);
    }
}