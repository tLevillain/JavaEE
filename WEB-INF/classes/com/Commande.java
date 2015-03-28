package com;

import javax.servlet.*;
import javax.servlet.http.*;
/**
 *Commande est l'interface que toutes les differentes commandes doivent respecter.
 *@author Brunot.Mermet
 *@version 1.0
 */
public interface Commande {
    /**
     *Execute l'action voulue avec req.
     *@param req
     *le contenu de la requete
     *@return next La prochaine page jsp a afficher.
     *@throws Exception Si jamais une exception est relevee.
     */	
    public String execute(HttpServletRequest req) throws Exception;
}     
