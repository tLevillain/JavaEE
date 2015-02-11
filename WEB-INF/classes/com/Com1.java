package com;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

// Exemple d'une commande qui, après avoir effectuée un traitement, passe l'info à la JSP suivante

public class Com1 extends HttpServlet{

    public static final String DONNEE      = "donnee";
    public static final String FORM        = "form";
    public static final String VUE_SUCCES  = "/afficheDonnee.jsp";
    public static final String VUE_FORM    = "/page1.jsp";

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        CreationDonnee cd = new CreationDonnee();

        /* Traitement de la requête et récupération du bean en résultant */
        Donnee don = cd.creerDonnee( request );

        /* Ajout du bean et de l'objet métier à l'objet requête */
        request.setAttribute( DONNEE, don );
        request.setAttribute( FORM, cd );

        if ( cd.getErreurs().isEmpty() ) {
            /* Si aucune erreur, alors affichage de la fiche récapitulative */
            this.getServletContext().getRequestDispatcher( VUE_SUCCES ).forward( request, response );
        } else {
            /* Sinon, ré-affichage du formulaire de création avec les erreurs */
            this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
        }
    }
}

