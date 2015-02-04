package com;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

// Exemple d'une commande qui, après avoir effectuée un traitement, passe l'info à la JSP suivante

public class Com1 extends HttpServlet{
  public static final String INPUT1 ="input1";
 	public static final String INPUT2 ="input2";

  public static final String VUE    ="/afficheDonnee.jsp";

  	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		String input1 = request.getParameter(INPUT1);
  		String input2 = request.getParameter(INPUT2);

  		String message;
  		boolean erreur;

  		if(input1.trim().isEmpty()) {
  			message = "Erreur - champ obligatoire vide. <br>";
  			erreur = true;
  		}	else {
  			message = "Formulaire rempli avec succès.";
  			erreur = false;
  		}

      Donnee don = new Donnee();

      don.setValeur1(input1);
      don.setValeur2(input2);

      request.setAttribute("donnee", don);
      this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
  	}
}

