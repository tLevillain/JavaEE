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
 *CreationCompte effectue la creation d'un compte pour l'application.
 *@author Thomas.Levillain
 *@version 0.1
 */
public class CreationCompte extends HttpServlet{

    private static final String CHAMP_NOM = "nom";
    private static final String CHAMP_MDP = "mdp";
    private static final String CHAMP_EMAIL = "email";
    private static final String SESSION_USER = "sessionUtilisateur";
    private static final String VUE = "/creationCompte.jsp";


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
     *Retourne le resultat du traitement
     *@return le resultat correspondant sous forme d'une chaine de caracteres.
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
	this.getServletContext().getRequestDispatcher("/creationCompte.jsp" ).forward( request, response );
    }
    
    /**
     *Traite les donnees et creer le compte correspondant si elles sont conformes.
     *@param req
     *Le contenu de la requete HTML.
     *@param rep
     *Le contenu de la reponse HTML.
     *@throw Exception Si jamais une exception est relevee.
     */
    public void doPost(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
	erreurs = new HashMap<String, String>();
	String email = getValeurChamp(req, CHAMP_EMAIL);
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
	    validationNom(nom, racine);
	} catch (Exception e) {
	    setErreur(CHAMP_NOM, e.getMessage());
	}

	try {
	    validationMDP(mdp);
	} catch (Exception e) {
	    setErreur(CHAMP_MDP, e.getMessage());
	}

	try {
	    validationEmail(email, racine);
	} catch (Exception e) {
	    setErreur(CHAMP_EMAIL, e.getMessage());
	}

	if(erreurs.isEmpty()) {
	    resultat = "Succes de la creation de compte";
	    try {
		 creationCompte(email, nom, mdp, racine, document);
	    } catch(Exception e) {
		resultat = "Erreur de lors de la creation " + e.getMessage();
	    }
	   
	} else {
	    resultat = "Echec de la creation de compte";    
	}
	req.setAttribute("resultat", resultat);
	req.setAttribute("erreur", erreurs);
	this.getServletContext().getRequestDispatcher(VUE).forward(req, rep);
    }	

    /**
     *Valide le nom en fonction de certains critères.
     *@param nom
     *La valeur du nom dans le formulaire
     *@param racine
     *La racine du document xml
     *@throws Exception Si jamais une exception est relevee.
     */
    public void validationNom(String nom, Element racine) throws Exception {
	if(nom==null || nom.trim().length() <= 3) {
	    throw new Exception("Merci d'entrer un nombre de plus de 3 caracteres.");
	}
	rechercheElement(nom, "nom", racine);
    }

    /**
     *Valide le email en fonction de certains critères.
     *@param email
     *La valeur de l'email dans le formulaire
     *@param racine
     *La racine du document xml
     *@throws Exception Si jamais une exception est relevee.
     */
    public void validationEmail(String email, Element racine) throws Exception {
	if(email==null || email.trim().length()<=5) {
	    throw new Exception("Merci d'entrer une email de plus de 5 caracteres.");
	}
	rechercheElement(email, "email", racine);
    }

    /**
     *Valide le mdp en fonction de certains critères.
     *@param mdp
     *La valeur du mdp dans le formulaire
     *@param racine
     *La racine du document xml
     *@throws Exception Si jamais une exception est relevee.
     */
    public void validationMDP(String mdp) throws Exception {
	if(mdp==null || mdp.trim().length()<=3) {
	    throw new Exception("Merci d'entrer un mdp de plus de 3 caracteres.");
	}
    }
    
    /**
     *Creer le compte utilisateur en fonction des champs precedemment verifies
     *@param email
     *L'email de l'utilisateur
     *@param nom
     *Le nom de l'utilisateur
     *@param mdp
     *Le mdp de l'utilisateur
     *@param racine
     *La racine du document xml
     *@param doc
     *Le document xml
     *@throws Exception Si jamais une exception est relevee.
     */
    public void creationCompte(String email, String nom, String mdp, Element racine, Document doc) throws Exception {
	Element inscrit = new Element("inscrits");
	racine.addContent(inscrit);
	Element elemEmail = new Element("email");
	elemEmail.setText(email);
	Element elemNom = new Element("nom");
	elemNom.setText(nom);
	Element elemMdp = new Element("mdp");
	elemMdp.setText(mdp);
	inscrit.addContent(elemEmail);
	inscrit.addContent(elemNom);
	inscrit.addContent(elemMdp);
	enregistreModifications(doc,"/home/syron/Documents/identifiants.xml" );
    }

    /**
     *Enregistre les modifications apportees au document xml.
     *@param document
     *Le document xml
     *@param fichier
     *Le chemin jusqu'au document
     *@throws Exception Si jamais une exception est relevee
     */
    static void enregistreModifications(Document document, String fichier) throws Exception
    {
	XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	sortie.output(document, new FileOutputStream(fichier));
    }

    /**
     *Recherche un element dans le document xml.
     *@param nom
     *Le contenu de l'element a rechercher
     *@param element
     *Le nom de l'element a recherche
     *@param racine
     *La racine du document xml
     *@throws Exception Si jamais l'element existe deja dans le document xml.
     */
    public void rechercheElement(String nom, String element, Element racine) throws Exception{
	List inscrits = racine.getChildren("inscrits");
	Iterator i = inscrits.iterator();
	boolean trouve = false;
	while(i.hasNext()) {
	    Element courant = (Element)i.next();
	    if(nom.equals(courant.getChild(element).getText())) {	
		trouve = true;
		break;		
	    }
	}	 
	if(trouve) {
	    throw new Exception(element + " deja present dans la liste des inscrits.");
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