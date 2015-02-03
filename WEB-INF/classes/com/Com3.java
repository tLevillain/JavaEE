package com;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.Random;

// Exemple d'une commande qui peut renvoyer vers une autre page que celle prévue

public class Com3 implements Commande {
    private String next;
    private Random generateur;

    public Com3(String next) {
	this.next = next;
	generateur = new Random();
    }

  public String execute(HttpServletRequest req) throws Exception {
      int rand = generateur.nextInt(4);
      String retour = next;
      if (rand == 0) {
	  retour = "/page1.jsp";
      }
      req.setAttribute("nombre", rand);
      return retour;
  }
}

