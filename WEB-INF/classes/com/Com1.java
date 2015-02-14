package com;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

// Exemple d'une commande qui, après avoir effectuée un traitement, passe l'info à la JSP suivante

public class Com1 implements Commande {

    private String next;
    public static final String TRAITEMENT  = "traitement";

    public Com1(String next){
	this.next = next;
    }

    public String execute(HttpServletRequest req) throws Exception {
	req.setAttribute(TRAITEMENT, "/creationDonnees");
	return next;
    }
}

