package model;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Position;

public class LectureNiveau {
	
	public  ArrayList<Niveau> levels = new ArrayList<>();
	private int current = 0 ;
	private int  col = 0 ;
	private int lig = 0 ;
	private int maxCol = 0;
	

	 public void lireNiveaux () throws IOException {
		 
		 File fich = new File("res/Niveaux/Original.txt");
		 
		 FileInputStream fis =  new FileInputStream(fich);
		 levels.add(new Niveau());
		 int n = fis.read();
		 
		 while(n > 0) {
			 char caracter = (char) n ;
			 Niveau currentLvl =  levels.get(current);
			 
			// Ignorer le retour chariot de Windows (\r) pour éviter les décalages
				if (caracter == '\r') {
					n = fis.read();
					continue;
				}
			 
			 if(caracter == '\n') {
				 //n = fis.read();
				 lig = lig + 1 ;
				 if(col > maxCol)
					 maxCol = col;
				 col = 0;
				 }
			 else if (caracter == '\t') {
				 //n = fis.read();
				 col = col +1 ;
			 }
			 else if (caracter == ';') {//fin du niveau
				 currentLvl.colonnes = maxCol ;
				 currentLvl.lignes = lig;
				 currentLvl.numeroLvl = current;
				 current  = current + 1;	
				 lig =0 ;
				 col = 0 ;
				 maxCol = 0;
				// On crée déjà l'objet pour le prochain niveau
				levels.add(new Niveau());
				// On consomme le reste de la ligne du ";" (souvent le numéro du niveau)
				while (n != '\n' && n > 0) {
					n = fis.read();
				}
				continue;
			 }
			 else if (caracter == '#') {//mur
				 currentLvl.cases[lig][col] = 1 ;
				 col = col + 1;
			 }
			 else if (caracter == '@') {//pousseur
				 currentLvl.cases[lig][col] = 2 ;
				 currentLvl.pousseurC =  col;
				 currentLvl.pousseurL = lig;
				 col = col + 1;
			 }
			 else if (caracter == '.') {//but
				 currentLvl.cases[lig][col] = 8 ;
				 Position pos = new Position(lig,col);
				 currentLvl.CasesBut.add(pos);
				 col = col + 1;
			 }
			 else if (caracter == '$') {//caisse
				 currentLvl.cases[lig][col] = 4 ;
				 col = col + 1;
			 }
			 else if (caracter == '*') { // Caisse sur un but 
					currentLvl.cases[lig][col] = 12; // 4 + 8
					Position pos = new Position(lig,col);
					 currentLvl.CasesBut.add(pos);
					col = col + 1;
			}
			else if (caracter == ' ') { // Case vide explicite // sol
					currentLvl.cases[lig][col] = 0;
					col = col + 1;
			}
			else if (caracter == '+') {//pousseur sur un but
				currentLvl.cases[lig][col] = 10;
				Position pos = new Position(lig,col);
				 currentLvl.CasesBut.add(pos);
				col = col + 1;
			}
			 n = fis.read();
		 }
		// Supprimer le dernier niveau vide créé en trop à la fin du fichier
			if (!levels.isEmpty() && levels.get(levels.size() - 1).lignes == 0) {
				levels.remove(levels.size() - 1);
			}
			
			fis.close();
		 		 
	 }


	 public ArrayList<Niveau> getLevels() {
		 return levels;
	 }


	 public void setLevels(ArrayList<Niveau> levels) {
		 this.levels = levels;
	 }
	 
	 
	 
}
