package com;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.lang.reflect.Constructor;


public class Controleur extends HttpServlet {
	private Map<String,Commande> map;
    private static final String JSP_ERREUR_INIT = "/erreurs/erreurInitControleur.jsp";
    private static final String JSP_ERREUR_CMD = "/erreurs/erreurCommande.jsp";
    private static final String JSP_EXCEPTION_CMD = "/erreurs/exceptionCommande.jsp";

	/*
	 * code de l'erreur éventuellement générée pendant
           l'initialisation de la hashmap (méthode init)
	   0 : pas d'erreur
	   -1 : classe de commande inexistante
	   -2 : constructeur de la classe de commande inexistant
	   -3 : erreur de création d'une instance de la classe de commande
	   -4 : un paramètre de l'application est mal initialisé (valeur mal structurée)
	   -7 : on essaie d'insérer deux fois le même nom de commande dans la HashMap
	 */
	private int erreur;
	private String messageErreur; 

	public void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException { 

		//on vérifie si la méthode init a déclenché une erreur
		if (erreur !=0) {
		   	req.setAttribute("code", erreur);
			req.setAttribute("mess", messageErreur);
			RequestDispatcher rd1 = req.getRequestDispatcher(JSP_ERREUR_INIT); 
			rd1.forward(req, res);
			return;
		}

		String next=null;
		//on récupère le nom de la commande à activer dans la requête reçue                               
        	String cmd = req.getParameter("cmd");      
        	if (cmd==null) { cmd="accueil"; }
		else {
			if (!map.containsKey(cmd)) {
				RequestDispatcher rd1 = req.getRequestDispatcher(JSP_ERREUR_CMD); 
				rd1.forward(req, res); 
				return;
			}
		}

		//on récupère l'objet de type Commande associé au nom de commande précédent
        	Commande cde = map.get(cmd);                 
		try {
			//on exécute la méthode execute de l'objet "commande" sélectionné
			//cette méthode renvoie le nom de la JSP qui devra être activée ensuite
        		next = cde.execute(req);
		} catch(Exception ex){
		  req.setAttribute("exception",ex.getMessage());
		  req.setAttribute("classeException",ex.getClass().getSimpleName());
		  req.setAttribute("classe",cde.getClass().getName());
		  next = JSP_EXCEPTION_CMD;
		}
		//on active la vue ie la page JSP cible
		RequestDispatcher rd = req.getRequestDispatcher(next); 
		rd.forward(req, res); 

	} //fin méthode service

	//Cette méthode est exécutée une seule fois lors de la création
	//de la servlet par tomcat. Ceci arrive lors de la première invocation
	//de la servlet
	public void init(ServletConfig config) throws ServletException {

		//une hashmap est utilisée pour mémoriser les triplets :
		//<nom commande, commande à utiliser, page cible>
		//"commande à utiliser" correspond à un objet instance d'une classe
		//de commande dont la méthode "execute" devra être exécutée par le controleur
		//Suite à cette exécution, la JSP appelée "page cible" devra être activée par tomcat.
		//Pour cela, le nom de cette page va être mémorisée dans l'objet commande activé pour
		//être renvoyé par la méthode execute. 
		map = new HashMap<String,Commande>();
		erreur = 0;

		//Enumeration<String> listeParametres = config.getServletContext().getInitParameterNames();
		Enumeration listeParametres = config.getServletContext().getInitParameterNames();

	    	while (listeParametres.hasMoreElements()) {
		    String nomParamCommande = ((String)listeParametres.nextElement());
			//chaque couple(classe,jsp) est stocké dans les paramètres d'initialisation sous la forme
			//d'un paramètre dont le nom est cmd-nom, avec nom : le nom de la commande
			if (!nomParamCommande.startsWith("cmd-")) {
			    continue;
			    // Si ce n'est pas une déclaration de commande, on ignore
			}
		    String ligne = config.getServletContext().getInitParameter(nomParamCommande);

			//la valeur du paramètre est une chaine de caractères où les éléments
			//du couple sont séparés par des points-virgule
        		String [] ligneDecomposee = ligne.split(";");
			if (ligneDecomposee.length != 2) {
				erreur = -4;
				messageErreur = "Dans le fichier web.xml, un param&egrave;tre est mal initialis&eacute; (";
				messageErreur += ligne + ")";
				return;
			}
			//on enlève les éventuels blancs "en trop" pour chacune des infos à récupérer
        		String nomCommande = nomParamCommande.substring(4).trim();
        		String nomClasseCommande = ligneDecomposee[0].trim();
        		String pageSuivante = ligneDecomposee[1].trim();
			//on demande à la machine virtuelle Java de charger la classe de l'objet commande
			//qui devra être activée
			Class<?> classeCommande = null;
			Constructor<?> consCommande = null;
			Commande commande = null;

			try {
			    classeCommande = Class.forName(nomClasseCommande);
			}
			catch (ClassNotFoundException ex) {
			      erreur = -1;
			      messageErreur = "Utilisation dans le fichier web.xml d'une classe de Commande inexistante (";
			      messageErreur += nomClasseCommande + ")";
			      return;
			}
			//on récupère dynamiquement le constructeur de cette classe
			//"String.class" signifie que le constructeur que l'on recherche prend un
			//élément de type String en paramètre (le nom de la page JSP cible)
			try {
        			consCommande = classeCommande.getConstructor(String.class);
			}
			catch (NoSuchMethodException ex) {
			      erreur = -2;
			      messageErreur = "Constructeur d'une classe de Commande inexistant";
			      return;
			}
			//on crée donc une objet instance de la classe de Commande concernée.
			//on passe en paramètre au constructeur le nom de la page JSP cible ie.
			//qui devra être activée par tomcat à la fin de l'exécution de la 
			//méthode execute de l'objet commande activé.
			try {
        			commande = ((Commande) consCommande.newInstance(pageSuivante));
			}
			catch (Exception ex) {
			      erreur = -3;
			      messageErreur = "Erreur pendant la cr&eacute;ation d'une instance d'une classe de Commande <br/>";
			      messageErreur += "Probablement dans le fichier web.xml, une classe autre qu'une classe";
			      messageErreur += " de Commande a été associ&eacute;e &agrave; un nom de commande";
			      return;
			}
			//on ajoute le "triplet" dans le Hashmap
			if (map.containsKey(nomCommande)) {
				erreur = -7;
				messageErreur = "Un nom de commande est utilis&eacute; 2 fois dans le fichier web.xml (";
				messageErreur += nomCommande + ")";
				return;
			}
        		map.put(nomCommande,commande);
	    } //fin du for                 
	} //fin méthode init

} //fin de la classe

