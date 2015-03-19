package com;
public class Donnee {
	private String valeur1;
	private String valeur2;

	public Donnee() {
		valeur1 = null;
		valeur2 = null;
	}

	public void setValeur1(String pValeur1) {
		valeur1 = pValeur1;
	} 

	public void setValeur2(String pValeur2) {
		valeur2 =pValeur2;
	}

	public String getValeur1() {
		return valeur1;
	}

	public String getValeur2() {
		return valeur2;
	}
}