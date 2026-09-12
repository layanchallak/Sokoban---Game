package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Jeu;
import view.Panneau;
import javax.swing.JFrame ;
public class ActionKeyboard implements KeyListener {
	

	private Jeu game;
	private Panneau pan; 
	private ActionButton buttonController;
	private JFrame fen;
	
	//  constructeur 
	public ActionKeyboard(Jeu game, Panneau pan, JFrame fen ) {
		this.game = game;
		this.pan = pan;
		this.fen = fen;
		this.buttonController = new ActionButton(game,pan);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		boolean aBouge = false;

		switch (keyCode) {
			case KeyEvent.VK_UP:
				game.deplacerPousseur(0); // Haut
				aBouge = true;
				pan.setPousseurImg(pan.getPousseurHaut());
				break;
			case KeyEvent.VK_DOWN:
				game.deplacerPousseur(1); // Bas
				aBouge = true;
				break;
			case KeyEvent.VK_RIGHT:
				game.deplacerPousseur(2); // Droite
				aBouge = true;
				pan.setPousseurImg(pan.getPousseurDroite());
				break;
			case KeyEvent.VK_LEFT:
				game.deplacerPousseur(3); // Gauche
				aBouge = true;
				pan.setPousseurImg(pan.getPousseurGauche());
				break;
			
		}

		// Si le joueur a tenté un mouvement, on redessine l'écran !
		if (aBouge && pan != null) {
			pan.repaint();
			//System.out.println("estternimer " + game.estTerminer());
			if(game.estTerminer()) {
				buttonController.nextLevel();
				
				if (fen != null) {
                    fen.requestFocus();
                }
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		boolean aBouge = false;

		switch (keyCode) {
			case KeyEvent.VK_UP:
				//pan.setPousseurImg(pan.getPousseurFace());
				//pan.repaint();
				break;
			case KeyEvent.VK_DOWN:
				pan.setPousseurImg(pan.getPousseurFace());
				pan.repaint();
				break;
			case KeyEvent.VK_RIGHT:
				//pan.setPousseurImg(pan.getPousseurFace());
				//pan.repaint();
				break;
			case KeyEvent.VK_LEFT:
				//pan.setPousseurImg(pan.getPousseurFace());
				//pan.repaint();
				break;
			
		}

		// Si le joueur a tenté un mouvement, on redessine l'écran !
		if (aBouge && pan != null) {
			pan.repaint();
			//System.out.println("estternimer " + game.estTerminer());
			if(game.estTerminer()) {
				buttonController.nextLevel();
			}
		}
		
	}
}

