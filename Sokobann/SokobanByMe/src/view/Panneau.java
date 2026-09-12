package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Niveau;

public class Panneau extends JPanel{
	
	public Niveau lvl ;
	public int width ;
	public int hauteur;
	public int largeurCase;
	public int hauteurCase;
	
	private Image pousseurImg; 
	private Image solImg ;
	private Image caisseImg ;
	private Image butImg;
	private Image murImg ;
	private Image caisseSurButImg;
	
	private Image pousseurGauche;
	private Image pousseurDroite;
	private Image pousseurFace;
	private Image pousseurHaut;
	private Image pousseurBas;
	
	
	
	public Panneau(Niveau lvl) {
		this.lvl = lvl;
		try {
			this.pousseurImg = ImageIO.read(new File ("res/Images/Pousseur.png"));
			this.pousseurBas = ImageIO.read(new File ("res/Images/Pousseur.png"));
			this.pousseurHaut = ImageIO.read(new File ("res/Images/Pousseur_0_0.png"));
			this.pousseurGauche = ImageIO.read(new File ("res/Images/Pousseur_1_0.png"));
			this.pousseurFace = ImageIO.read(new File ("res/Images/Pousseur_2_0.png"));
			this.pousseurDroite = ImageIO.read(new File ("res/Images/Pousseur_3_0.png"));
			this.murImg = ImageIO.read(new File ("res/Images/Mur.png"));
			this.solImg = ImageIO.read(new File ("res/Images/Sol.png"));
			this.caisseImg = ImageIO.read(new File ("res/Images/Caisse.png"));
			this.caisseSurButImg = ImageIO.read(new File ("res/Images/Caisse_sur_but.png"));
			this.butImg = ImageIO.read(new File ("res/Images/But.png"));
		} catch (IOException e) {
			System.err.println("Erreur de chargement des images. Vérifie le dossier ../res/Images/");
			e.printStackTrace();
		}
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (this.lvl == null) return;
		
		int largeurCase = this.getWidth() / this.lvl.colonnes;
		int hauteurCase = this.getHeight() / this.lvl.lignes;
		
		// Pour garder des cases parfaitement carrées, on peut prendre le minimum des deux
		int tailleCase = Math.min(largeurCase, hauteurCase);
		if (tailleCase <= 0) tailleCase = 32; // Valeur de secours au démarrage
		
		for(int i = 0 ;  i < this.lvl.lignes ;  i = i+1) {
			for (int j = 0 ; j < this.lvl.colonnes ; j = j + 1 ) {
				
				// Coordonnées graphiques : X correspond aux colonnes (j), Y aux lignes (i)
				int x = j * tailleCase;
				int y = i * tailleCase;
				
				// On dessine  le sol en fond
				g.drawImage(solImg, x, y, tailleCase, tailleCase, null);
				
				switch(this.lvl.cases[i][j]) {
					case 1 ://MUR
						g.drawImage(murImg,x, y, tailleCase, tailleCase, null);
						break;
					case 2 ://Pousseur
						g.drawImage(pousseurImg, x, y, tailleCase, tailleCase, null);
						break;
					case 4 ://CAISSE
						g.drawImage(caisseImg,x, y, tailleCase, tailleCase, null);
						break;
					case 8 : // BUT
						g.drawImage(butImg, x, y, tailleCase, tailleCase, null);
						break;
					case 12: // CAISSE SUR BUT (4 + 8)
						g.drawImage(caisseSurButImg,x, y, tailleCase, tailleCase, null);
						break;
					case 10 ://Pousseur sur un but 
						g.drawImage(butImg,x, y, tailleCase, tailleCase, null);
						g.drawImage(pousseurImg, x, y, tailleCase, tailleCase, null);
						break;

					default:
						// Déjà géré par le solImg au-dessus
						break;
				}
				
			}
			
		}
		
	}
		
	public Niveau getLvl() { 
		return lvl; 
	}
	
	public void setLvl(Niveau lvl) {
		this.lvl = lvl;
		this.repaint();
	}

	public Image getPousseurImg() {
		return pousseurImg;
	}

	public void setPousseurImg(Image pousseurImg) {
		this.pousseurImg = pousseurImg;
	}

	public Image getPousseurGauche() {
		return pousseurGauche;
	}

	public void setPousseurGauche(Image pousseurGauche) {
		this.pousseurGauche = pousseurGauche;
	}

	public Image getPousseurDroite() {
		return pousseurDroite;
	}

	public void setPousseurDroite(Image pousseurDroite) {
		this.pousseurDroite = pousseurDroite;
	}


	public Image getPousseurHaut() {
		return pousseurHaut;
	}

	public void setPousseurHaut(Image pousseurHaut) {
		this.pousseurHaut = pousseurHaut;
	}

	public Image getPousseurBas() {
		return pousseurBas;
	}

	public void setPousseurBas(Image pousseurBas) {
		this.pousseurBas = pousseurBas;
	}

	public Image getPousseurFace() {
		return this.pousseurFace;
	}

	
	
}
