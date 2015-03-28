package com;

import java.io.*;
import java.util.List;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
/**
 *editionXML permet de lire un fichier xml et d'en recuperer son contenu.
 *@author Thomas.levillain
 *@version 0.1
 */
public class editionXML implements Commande{
    
    /**
     *La prochaine page jsp a afficher.
     */
    private String next;

    /**
     *Constructeur editionXML
     *@param next
     *La prochaine page jsp a afficher.
     */
    public editionXML(String next) {
	this.next = next;
    }

    /**
     *Recupere le contenu du fichier XML et passe la main a next.
     *@param req
     *le contenu de la requete
     *@return next La prochaine page a afficher
     *@throws Exception si jamais une exception est relevee.
     */
    public String execute(HttpServletRequest req) throws Exception {
	String texte = lireXML();
	req.setAttribute("xml", texte);
	return next;
    }

    /**
     *Recupere le contenu d'un fichier XML 
     *@return texte le contenu du fichier XML.
     */
    public String lireXML() {
	String texte = "";
	try {
	    BufferedInputStream in = new BufferedInputStream(new FileInputStream("/home/syron/Documents/AZERTY.xml"));
	    StringWriter out = new StringWriter();
	    int b;
	    while ((b=in.read()) != -1)
		out.write(b);
	    out.flush();
	    out.close();
	    in.close();
	    texte =  out.toString();
	}
	catch (IOException ie)
	    {
		ie.printStackTrace(); 
	    }
	return texte;
    }
}