package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ActionButton;
import controller.ActionKeyboard;
import model.Jeu;

public class InterfaceGraphic  {
	
	private Jeu game  ;
	
	private Panneau pan ;
	private ActionKeyboard keyController;
	
	private ActionButton ButtonController;
	
	
	public void run() {
		JFrame fen = new JFrame();
		fen.setTitle("Sokoban");
		//fen.setSize(600, 600);
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//fen.setLocationRelativeTo(null);
		
		// (Adapte selon la méthode exacte de ta classe Jeu, ex: game.getNiveauActuel())
		if (game != null && game.getNiveauActuel() != null) {
			pan = new Panneau(game.getNiveauActuel());
		} else {
					// Sécurité temporaire si ton objet game n'est pas encore lié
					// pan = new Panneau(new model.Niveau(10, 10, "Test"));
		}
		
		//  Initialisation du contrôleur en lui passant le jeu et le panneau
		keyController = new ActionKeyboard(game, pan , fen);
		ButtonController = new ActionButton(game , pan);
		
		JButton nextLvlButton = new JButton("Next level");
		//nextLvlButton.addActionListener(e -> ButtonController.nextLevel() );
		nextLvlButton.addActionListener(e -> {
		    ButtonController.nextLevel();
		    fen.requestFocus();
		});
		JButton previousLvlButton = new JButton("Previous level");
		//previousLvlButton.addActionListener(e -> ButtonController.previousLevel() );
		previousLvlButton.addActionListener(e -> {
		    ButtonController.previousLevel();
		    fen.requestFocus();
		});
		JButton restButton = new JButton("Restart");
		restButton.addActionListener(e -> {
		    ButtonController.restLevel();
		    fen.requestFocus();
		});
		JPanel container = new JPanel();
		container.add(nextLvlButton);
		container.add(previousLvlButton);
		container.add(restButton);
		
		fen.addKeyListener(keyController);
		
		//  On force la fenêtre à se concentrer sur les entrées clavier
		fen.setFocusable(true);
		fen.requestFocusInWindow();
		
		fen.setLayout(new BorderLayout());
		fen.add(pan, BorderLayout.CENTER);
		fen.add(container, BorderLayout.SOUTH);
		fen.setVisible(true); // INDISPENSABLE pour afficher la fenêtre
	}


	public Jeu getGame() {
		return game;
	}


	public void setGame(Jeu game) {
		this.game = game;
	}
	
	
	}
	
