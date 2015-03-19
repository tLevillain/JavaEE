package com;

import java.util.HashMap;
import java.util.Map;
import java.io.*;

import javax.servlet.http.HttpServletRequest;

public class CreationDonnee implements Commande {
    private static final String CHAMP_INPUT1    ="input1";
    private static final String CHAMP_INPUT2    ="input2";

    private String              resultat;
    private Map<String, String> erreurs         = new HashMap<String, String>();

    private String next;

    public CreationDonnee(String next){
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
	Donnee d = creerDonnee(req);
	req.setAttribute("donnee", d);
	jDOM jd = new jDOM();
	jd.creationXML(d);
	return next;
    }

    public Donnee creerDonnee( HttpServletRequest request ) {
        String input1      = getValeurChamp( request, CHAMP_INPUT1 );
        String input2      = getValeurChamp( request, CHAMP_INPUT2 );

        Donnee donnee = new Donnee();

        try {
            validationInput1( input1 );
        } catch ( Exception e ) {
            setErreur( CHAMP_INPUT1, e.getMessage() );
        }
        donnee.setValeur1( input1 );
        try {
            validationInput2( input2 );
        } catch ( Exception e ) {
            setErreur( CHAMP_INPUT2, e.getMessage() );
        }
        donnee.setValeur2( input2 );

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la création des données.";
        } else {
            resultat = "Échec de la création des données.";
        }

        return donnee;
    }

    private void validationInput1( String valeur ) throws Exception {
        if ( valeur != null ) {
            if ( valeur.length() < 2 ) {
                throw new Exception( "La chaîne doit contenir au moins 2 caractères." );
            }
        } else {
            throw new Exception( "Merci d'entrer une valeur." );
        }
    }

    private void validationInput2( String valeur ) throws Exception {
        if ( valeur != null ) {            
	    //throw new Exception( "La chaîne ne doit contenir que des caractères." );          
        } else {
            throw new Exception( "Merci d'entrer une valeur." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
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