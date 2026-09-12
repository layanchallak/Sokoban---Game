package model;

import java.util.ArrayList;

public class Niveau {
	
	static final int VIDE = 0;
	static final int MUR = 1;
	static final int POUSSEUR = 2;
	static final int CAISSE = 4;
	static final int BUT = 8;
	static final int ButCaisse = 12;
	static final int ButPousseur = 10;
	
	public int lignes, colonnes;
	public int[][] cases ;
	public String nom;
	public int numeroLvl;
	public ArrayList<Position> CasesBut ;
	int  pousseurL;
	int pousseurC;
	
	public Niveau(int l, int c, String nom) {
		cases = new int [l][c];
		this.lignes = l;
		this.colonnes = c;
		this.nom = nom;
		this.CasesBut = new ArrayList<>();
	}
	
	Niveau() {
		this.cases = new int[50][50]; 
		this.lignes = 0;
		this.colonnes = 0;
		this.CasesBut = new ArrayList<>();
	}
	
	public Niveau copie() {
	    Niveau n = new Niveau();

	    n.lignes = this.lignes;
	    n.colonnes = this.colonnes;
	    n.numeroLvl = this.numeroLvl;

	    n.pousseurL = this.pousseurL;
	    n.pousseurC = this.pousseurC;

	    for(int i = 0; i < lignes; i++) {
	        for(int j = 0; j < colonnes; j++) {
	            n.cases[i][j] = this.cases[i][j];
	        }
	    }
	    
	    for(Position pos : this.CasesBut) {
	    	n.CasesBut.add(pos);
	    }

	    return n;
	}

	public int getPousseurL() {
		return pousseurL;
	}

	public void setPousseurL(int pousseurL) {
		this.pousseurL = pousseurL;
	}

	public int getPousseurC() {
		return pousseurC;
	}

	public void setPousseurC(int pousseurC) {
		this.pousseurC = pousseurC;
	}
	
	
}
