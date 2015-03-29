package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Deconnect implements Commande {
    private String next;

    public Deconnect(String next) {
	this.next = next;
    }

    public String execute(HttpServletRequest req) throws Exception {
	HttpSession session = req.getSession();
        session.invalidate();

	return next;
    }
}