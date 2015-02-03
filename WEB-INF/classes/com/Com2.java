package com;

import javax.servlet.*;
import javax.servlet.http.*;

// Exemple d'une commande qui, après avoir effectuée un traitement, passe l'info à la JSP suivante

public class Com2 implements Commande {
  private String next;

  public Com2(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
      req.setAttribute("message", "Info renvoyée par la commande 2");
      return next;
  }
}

