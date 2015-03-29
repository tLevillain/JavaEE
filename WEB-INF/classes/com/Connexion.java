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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jdom2.*;
import org.jdom2.output.*;
import org.jdom2.input.*;
import org.jdom2.filter.*;
import java.util.List;
import java.util.Iterator;
import java.io.*;
/**
 *Connexion est la classe qui permet la connexion au service ESMA
 *@author Thomas.Levillain$
 *@version 0.1
 */
public class Connexion extends HttpServlet{

    private static final String CHAMP_NOM = "nom";
    private static final String CHAMP_MDP = "mdp";
    private static final String SESSION_USER = "sessionUtilisateur";
    private static final String VUE_CORRECTE = "/controleur";
    private static final String VUE_ERREUR = "/connexion.jsp";

    private String resultat;
    private Map<String, String> erreurs;

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
     *Redirige sur la page de creation de compte
     *@param request
     *Le contenu de la requete HTML.
     *@param response
     *Le contenu de la reponse HTML.
     *@throw Exception Si jamais une exception est relevee.
     */
    public void doGet( HttpServletRequest request, HttpServletResponse response )   throws ServletException, IOException {
	HttpSession session = request.getSession();
        session.invalidate();
	this.getServletContext().getRequestDispatcher("/connexion.jsp" ).forward( request, response );
    }
    
    /**
     *Traite les donnes et connecte la personne si elles sont corrects.
     *@param req
     *Le contenu de la requete HTML.
     *@param rep
     *Le contenu de la reponse HTML.
     *@throw Exception Si jamais une exception est relevee.
     */
    public void doPost(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
	erreurs = new HashMap<String, String>();
	String nom = getValeurChamp(req, CHAMP_NOM);
	String mdp = getValeurChamp(req, CHAMP_MDP);
	String vue = null;
	Document document = new Document();
	Element racine;
	//On crée une instance de SAXBuilder
	SAXBuilder sxb = new SAXBuilder();
	try
	    {
		//On crée un nouveau document JDOM avec en argument le fichier XML
		//Le parsing est terminé ;)
		document = sxb.build(new File("/home/syron/Documents/identifiants.xml"));
	    }
	catch(Exception e){}

	//On initialise un nouvel élément racine avec l'élément racine du document.
	racine = document.getRootElement();
	
	try {
	    validationIdentification(nom, mdp, racine);
	} catch (Exception e) {
	    setErreur("resultat", e.getMessage());
	}

	Utilisateur util = new Utilisateur(nom,mdp);
	HttpSession session = req.getSession();
	if(erreurs.isEmpty()) {
	    resultat = "Succes de la connexion";
	    session.setAttribute(SESSION_USER, util);
	    vue = VUE_CORRECTE;
	} else {
	    resultat = "Echec de la connexion";
	    session.setAttribute(SESSION_USER, null);
	    req.setAttribute("resultat", resultat);
	    req.setAttribute("erreur", erreurs);
	    vue = VUE_ERREUR;
	}

	this.getServletContext().getRequestDispatcher(vue).forward(req, rep);
    }	

    /**
     *Regarde si l'utilisateur existe reellement.
     *@param nom
     *Le nom rentre par l'utilisateur
     *@param mdp
     *Le mdp rentre par l'utilisateur
     *@param racine
     *La racine du document xml
     */
    public void validationIdentification(String nom, String mdp, Element racine) throws Exception{
	if(nom==null || mdp==null) {
	    throw new Exception("erreur");
	}
	List inscrits = racine.getChildren("inscrits");
	Iterator i = inscrits.iterator();
	boolean trouve = false;
	while(i.hasNext()) {
	    Element courant = (Element)i.next();
	    if(nom.equals(courant.getChild("nom").getText())) {
		if(mdp.equals(courant.getChild("mdp").getText())) {
		    trouve = true;
		    break;
		}	
	    }
	}	 
	if(!trouve) {
	    throw new Exception("erreur");
	}
    }

    /**
     *Ajoute une erreur a la liste des erreurs
     *@param champ
     *le nom du champ concerne
     *@param message
     *le type d'erreur
     */
    public void setErreur(String champ, String message) {
	erreurs.put(champ,message);
    }

    /**
     *Retourne la valeur d'un champ et vérifie si il est rempli
     *@param request
     *le contenu de la requete
     *@param nomChamp
     *le nom du champ
     *@return La valeur du champ sous la forme d'une chaine de caracteres
     */
     private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}