package com;

import java.io.*;
import org.jdom2.*;
import org.jdom2.output.*;

public class jDOM {
    private Element racine              = new Element("racine");
    private org.jdom2.Document document = new Document(racine);

    public jDOM(){}

    public void creationXML(Donnee donnee){
	if(estRempli(donnee)){
	    Element element1 = new Element("element1");
	    racine.addContent(element1);
	
	    Attribute attribute1 = new Attribute("attribut1", "toto");
	    element1.setAttribute(attribute1);
	    element1.setText(donnee.getValeur1());
	    Element element2 = new Element("element2");
	    element2.setText(donnee.getValeur2());
	    element1.addContent(element2);
	    //affiche();
	    enregistre("/home/syron/Documents/AZERTY.xml");
	}
    }

    public void affiche(){
	try
	    {	     
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		sortie.output(document, System.out);
	    }
	catch (java.io.IOException e){}
    }

    public void enregistre(String fichier)
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