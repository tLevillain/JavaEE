package com;

import java.io.*;
import org.jdom2.*;
import org.jdom2.output.*;

public class jDOM {
    static Element racine              = new Element("racine");
    static org.jdom2.Document document = new Document(racine);

    public static void creationXML(Donnee donnee){
	if(estRempli(donnee)){
	    Element element1 = new Element("element1");
	    racine.addContent(element1);
	
	    Attribute attribute1 = new Attribute("attribut1", donnee.getValeur1());
	    element1.setAttribute(attribute1);

	    Element element2 = new Element("element2");
	    element2.setText(donnee.getValeur2());
	    element1.addContent(element2);
	    //affiche();
	    enregistre("/home/syron/Documents/AZERTY.xml");
	}
    }

    public static void affiche(){
	try
	    {	     
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		sortie.output(document, System.out);
	    }
	catch (java.io.IOException e){}
    }

    public static void enregistre(String fichier)
    {
	try
	    {		
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		sortie.output(document, new FileOutputStream(fichier));
	    }
	catch (java.io.IOException e){}
    }

    public static boolean estRempli(Donnee donnee){
	if(donnee.getValeur1()==null || donnee.getValeur2()==null){
	    return false;
	}
	return true;
    }
}