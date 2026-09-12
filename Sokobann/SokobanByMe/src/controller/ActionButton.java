package controller;

import java.io.IOException;

import javax.swing.JButton;

import model.Jeu;
import model.LectureNiveau;
import model.Niveau;
import view.Panneau;

public class ActionButton  {
	
	private Jeu game ;
	private Panneau pan ;
	private LectureNiveau lecteur;
	
	public ActionButton(Jeu game, Panneau pan ) {
		this.game = game;
		this.pan = pan;
		this.lecteur =  this.game.getLec();
		
	}
	
	public void nextLevel() {
		Niveau currentlvl = game.getNiveauActuel();
		if (currentlvl.numeroLvl == this.lecteur.levels.size()) return;
		Niveau newLvl =  lecteur.getLevels().get( currentlvl.numeroLvl + 1  ).copie();
		game.setNiveauActuel(newLvl);
		pan.setLvl(newLvl); 
		pan.repaint();
		pan.requestFocusInWindow();
		
	}
	
	public void previousLevel() {
		Niveau currentlvl = game.getNiveauActuel();
		if(currentlvl.numeroLvl == 0) return;
		Niveau newLvl =  lecteur.getLevels().get(currentlvl.numeroLvl - 1 ).copie();
		game.setNiveauActuel(newLvl);
		pan.setLvl(newLvl); 
		pan.repaint();
		pan.requestFocusInWindow();
		
	}
	
	public void restLevel() {
		Niveau currentlvl = game.getNiveauActuel();
		Niveau newLvl =  lecteur.getLevels().get(currentlvl.numeroLvl ).copie();
		game.setNiveauActuel(newLvl);
		pan.setLvl(newLvl); 
		pan.repaint();
		pan.requestFocusInWindow();
	}
	

	public Jeu getGame() {
		return game;
	}

	public void setGame(Jeu game) {
		this.game = game;
	}

	public Panneau getPan() {
		return pan;
	}

	public void setPan(Panneau pan) {
		this.pan = pan;
	}	
	
}
