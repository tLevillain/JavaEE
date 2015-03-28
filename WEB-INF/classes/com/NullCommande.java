package com;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *NullCommande ne fait rien sinon de passer la main a next.
 *@author Brunot.Mermet
 *@version 1.0
 */
public class NullCommande implements Commande {
    /**
     *La prochaine page jsp a afficher.
     */
  private String next;

    /**
     *Constructeur NullCommande.
     *@param next
     *La prochaine page jsp a afficher.
     */
  public NullCommande(String next) {
        this.next = next;
  }

    /**
     *Passe la main a next.
     *@param req
     *le contenu de la requete
     *@return next La prochaine page a afficher.
     *@throws Exception Si jamais une exception est relevee.
     */
  public String execute(HttpServletRequest req) throws Exception {
    return next;
  }
}

