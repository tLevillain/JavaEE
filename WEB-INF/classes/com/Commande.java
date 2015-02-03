package com;

import javax.servlet.*;
import javax.servlet.http.*;

public interface Commande {
	public String execute(HttpServletRequest req) throws Exception;
}     
