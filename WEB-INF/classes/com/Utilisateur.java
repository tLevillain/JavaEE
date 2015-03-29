package com;

public class Utilisateur {
    private String nom;
    private String mdp;

    public Utilisateur() {
	this.nom=null;
	this.mdp=null;
    }

    public Utilisateur(String nom, String mdp) {
	this.nom=nom;
	this.mdp=mdp;
    }

    public String getNom() {
	return nom;
    }

    public String getMDP() {
	return mdp;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }

    public void setMDP(String mdp) {
	this.mdp=mdp;
    }
}