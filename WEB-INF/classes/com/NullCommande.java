package com;

import javax.servlet.*;
import javax.servlet.http.*;

// Cette cde ne fait rien sinon de passer la main a next

public class NullCommande implements Commande {
  private String next;

  public NullCommande(String next) {
        this.next = next;
  }

  public String execute(HttpServletRequest req) throws Exception {
    return next;
  }
}

