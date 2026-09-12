package main;

import java.io.IOException;
import model.Jeu;
import model.LectureNiveau;
import view.InterfaceGraphic;

public class Main {

	public static void main(String[] args) {
		// 1. Création du lecteur de niveaux
		LectureNiveau lecteur = new LectureNiveau();
		
		try {
			System.out.println("Chargement des niveaux...");
			lecteur.lireNiveaux();
			
			// Vérification de sécurité
			if (lecteur.getLevels().isEmpty()) {
				System.err.println("Erreur : Aucun niveau n'a été trouvé dans le fichier.");
				return;
			}
			
			// 2. Initialisation de la logique du jeu avec le premier niveau (index 0)
			Jeu jeu = new Jeu();
			jeu.setLec(lecteur);
			jeu.setNiveauActuel(lecteur.getLevels().get(0).copie());
			System.out.println(lecteur.getLevels().get(0).numeroLvl);
			
			// 3. Configuration et lancement de l'interface graphique
			//System.out.println("Lancement de l'interface graphique...");
			InterfaceGraphic gui = new InterfaceGraphic();
			gui.setGame(jeu); // On lie le jeu à l'interface
			gui.run();
			
			System.out.println("Le jeu est prêt !");
			
		} catch (IOException e) {
			System.err.println("Impossible de lire le fichier de niveaux. Vérifie le chemin d'accès.");
			e.printStackTrace();
		}
	}
}