package com;

import java.io.*;
import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.filter.*;
import java.util.List;
import java.util.Iterator;

public class editionXML {
    static org.jdom2.Document document;
    static Element racine;

    public static Donnee edition(){
	//On crée une instance de SAXBuilder
	SAXBuilder sxb = new SAXBuilder();
	try
	    {
		//On crée un nouveau document JDOM avec en argument le fichier XML
		document = sxb.build(new File("/home/syron/Documents/AZERTY.xml"));
	    }
	catch(Exception e){}
	
	Donnee don = new Donnee();

	//On initialise un nouvel élément racine avec l'élément racine du document.
	racine = document.getRootElement();
	List listElement  = racine.getChildren("element1");
	Iterator i = listElement.iterator();
	Element courant = (Element)i.next();
	
	don.setValeur1(courant.getText().trim());
	don.setValeur2(courant.getChild("element2").getText().trim());
	return don;
    }
}