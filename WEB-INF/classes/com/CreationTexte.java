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
/**
 *CreationTexte est la classe qui effectue le traitement d'une zone texte pour creer le fichier XML correspondant
 *tout en respectant le fichier DTD lie au fichier.
 *
 *@author Thomas.Levillain
 *@version 0.1
 */
public class CreationTexte implements Commande {
    /**
     *Le nom du champ contenant le texte
     */
    private static final String CHAMP_TEXTE    ="mybox";
    /**
     *Le nom du champ contenant le nom du fichier
     */
    private static final String CHAMP_NOM      ="nomFichier";

    /**
     *Le resultat final du traitement les differents champs.
     */
    private String resultat;
    /**
     *La liste des differentes erreurs.
     */
    private Map<String, String> erreurs;

    /**
     *La prochaine page jsp a afficher.
     */
    private String next;

    /**
     *Contructeur CreationTexte.
     *@param next
     *La prochaine page jsp a afficher.
     */
    public CreationTexte(String next){
	this.next = next;
    }

    /**
     *Retourne la liste des erreurs.
     *@return les erreurs correspondantes, sous forme d'une Map de chaines de caracteres.
     */
    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    /**
     *Retourne le resultat du traitement.
     *@return le resultat correspondant sour forme d'une chaine de caracteres.
     */
    public String getResultat() {
        return resultat;
    }

    /**
     *Traite CHAMP_NOM et CHAMP_TEXTE, verifie les erreurs et génère le fichier "nom".xml avec le contenu de CHAMP_TEXTE
     *@param req
     *le contenu de la requete HTML.
     *@return next La prochaine page a afficher.
     *@throws Exception Si jamais une exception est relevée.
     */
    public String execute(HttpServletRequest req) throws Exception 
    {
	//Initialisation des variables
	erreurs = new HashMap<String, String>();
	resultat = "";
	//Récupération des différents champs
	String texte = getValeurChamp(req, CHAMP_TEXTE).trim();
	String nom = getValeurChamp(req, CHAMP_NOM);
	
	//Validation du nom
	try {
	    validationNom(nom);
	} catch(Exception e) {
	    setErreur(CHAMP_NOM, e.getMessage());
	}
	nom = nom + ".xml";
	//Validation du contenu texte
	try {
	    validationTexte(texte);
	    creerXML(texte, nom);
	} catch(Exception e) {
	    setErreur(CHAMP_TEXTE, e.getMessage());
	}
	
	//Si il n'y a pas d'erreurs, on indique le résultat positif
	if(erreurs.isEmpty()) {
	    resultat = "Document " + nom + " créé avec succés.";
	} else {
	    resultat ="";
	}
	//Attibution des différentes attributs au champ req
	req.setAttribute("resultat", resultat);
	req.setAttribute("erreur", erreurs);
	req.setAttribute("xml", texte);
	req.setAttribute("nom", getValeurChamp(req, CHAMP_NOM));
	return next;
    }

    /**
     *Creer un fichier XML avec son contenu texte et son nom
     *@param texte
     *Le contenu texte du fichier
     *@param nom
     *Le nom du fichier
     */
    private void creerXML(String texte, String nom) throws IOException, Exception {
	PrintWriter out = new PrintWriter("/home/syron/Documents/" + nom);
	out.println(texte);
	out.close();
	SAXBuilder builder = new SAXBuilder(XMLReaders.DTDVALIDATING);
        Document doc = builder.build(new File("/home/syron/Documents/" + nom));
    }

    /**
     *Retourne la valeur d'un champ et vérifie si il est rempli
     *@param request
     *le contenu de la requete
     *@param nomChamp
     *le nom du champ
     *@return La valeur du champ sous la forme d'une chaine de caracteres
     */
    private String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

    /**
     *Valide le contenu du texte
     *@param texte
     *le contenu du texte
     *@throws Exception Si jamais le texte est vide
     */
    private void validationTexte(String texte) throws Exception {
	if(texte==null || texte.trim() == "") {
	    throw new Exception("Merci d'entrer une valeur");
	}
    }

     /**
     *Valide le contenu du texte
     *@param texte
     *le contenu du texte
     *@throws Exception Si jamais le texte est vide
     */
    private void validationNom(String nom) throws Exception {
	if(nom==null || nom.trim()== "") {
	    throw new Exception("Merci d'entrer une valeur");
	}
    }

    /**
     *Ajoute une erreur a la liste des erreurs
     *@param champ
     *le nom du champ concerne
     *@param message
     *le type d'erreur
     */
    private void setErreur(String champ, String message) {
	erreurs.put(champ, message);
    }
}