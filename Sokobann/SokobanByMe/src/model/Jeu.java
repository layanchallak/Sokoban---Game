package model;

import controller.ActionKeyboard;

public class Jeu {
	private Niveau niveauActuel ;
	private LectureNiveau lec ;
	
	
	
	public Jeu(Niveau n, LectureNiveau lec) {
		this.niveauActuel = n;
		this.lec = lec;
	}
	
	public Jeu() {
		super();
	}
	
	/*public void deplacerPousseur(int direction) { // 0: haut, 1: bas, 2: droite, 3: gauche
		if (this.niveauActuel == null) return;

		int ligP = this.niveauActuel.pousseurL;
		int colP = this.niveauActuel.pousseurC;

		// Détermination du changement de coordonnées selon la direction (Vecteurs)
		int dl = 0; // Changement de ligne
		int dc = 0; // Changement de colonne

		switch (direction) {
			case 0: dl = -1; break; // Haut
			case 1: dl = 1;  break; // Bas
			case 2: dc = 1;  break; // Droite
			case 3: dc = -1; break; // Gauche
		}

		// Case ciblée par le pousseur 
		int ligCible = ligP + dl;
		int colCible = colP + dc;

		// Case située derrière la case ciblée
		int ligDerriere = ligCible + dl;
		int colDerriere = colCible + dc;

		int valeurCible = this.niveauActuel.cases[ligCible][colCible];

		// CAS 1 : La case cible est un MUR (1) -> Impossible de bouger
		if (valeurCible == Niveau.MUR) {
			System.out.println("Impossible : C'est un mur !");
			return;
		}

		// CAS 2 : La case cible est VIDE (0) ou est un BUT (8)
		if (valeurCible == Niveau.VIDE || valeurCible == Niveau.BUT) {
			
			// On enlève le pousseur de son ancienne case
			// Si le pousseur était sur un but, la case redevient un BUT (8), sinon VIDE (0)
			if (this.niveauActuel.cases[ligP][colP] == (Niveau.POUSSEUR + Niveau.BUT)) {
				this.niveauActuel.cases[ligP][colP] = Niveau.BUT;
			} else {
				this.niveauActuel.cases[ligP][colP] = Niveau.VIDE;
			}

			// On installe le pousseur sur la nouvelle case
			if (valeurCible == Niveau.BUT) {
				this.niveauActuel.cases[ligCible][colCible] = Niveau.POUSSEUR + Niveau.BUT; // Valeur 10
			} else {
				this.niveauActuel.cases[ligCible][colCible] = Niveau.POUSSEUR; // Valeur 2
			}

			// IMPORTANT : On met à jour la position du pousseur dans l'objet Niveau
			this.niveauActuel.pousseurL = ligCible;
			this.niveauActuel.pousseurC = colCible;
			return;
		}

		// CAS 3 : La case cible contient une CAISSE (4 ou 12 si sur but)
		if (valeurCible == Niveau.CAISSE || valeurCible == 12) {
			int valeurDerriere = this.niveauActuel.cases[ligDerriere][colDerriere];

			// On ne peut pousser la caisse que si derrière elle c'est VIDE (0) ou un BUT (8)
			if (valeurDerriere == Niveau.VIDE || valeurDerriere == Niveau.BUT) {
				
				// 1. Déplacer la caisse vers la case "derrière"
				if (valeurDerriere == Niveau.BUT) {
					this.niveauActuel.cases[ligDerriere][colDerriere] = 12; // Caisse sur but (4+8)
				} else {
					this.niveauActuel.cases[ligDerriere][colDerriere] = Niveau.CAISSE; // Caisse simple (4)
				}

				// 2. Déplacer le pousseur sur la case où était la caisse (case cible)
				if (this.niveauActuel.cases[ligP][colP] == (Niveau.POUSSEUR + Niveau.BUT)) {
					this.niveauActuel.cases[ligP][colP] = Niveau.BUT;
				} else {
					this.niveauActuel.cases[ligP][colP] = Niveau.VIDE;
				}

				if (valeurCible == 12) { // La caisse était sur un but, donc le pousseur arrive sur un but
					this.niveauActuel.cases[ligCible][colCible] = Niveau.POUSSEUR + Niveau.BUT;
				} else {
					this.niveauActuel.cases[ligCible][colCible] = Niveau.POUSSEUR;
				}

				// Mise à jour des coordonnées du pousseur
				this.niveauActuel.pousseurL = ligCible;
				this.niveauActuel.pousseurC = colCible;
				//System.out.println("Caisse poussée !");
			} else {
				System.out.println("Impossible : La caisse est bloquée par un obstacle.");
			}
		}
		
	}*/

	
	public void deplacerPousseur(int direction) {//0: haut ; 1:bas ; 2 : droite ; 3:gauche
		
		if (this.niveauActuel == null) return;

		int ligP = this.niveauActuel.pousseurL;
		int colP = this.niveauActuel.pousseurC;
		int newligP = 0 ;
		int newcolP = 0 ;
		// Détermination du changement de coordonnées selon la direction (Vecteurs)
		int dl = 0; // Changement de ligne
		int dc = 0; // Changement de colonne
		
		switch (direction) {
			case 0: dl = -1; newligP = ligP + dl ; newcolP = colP + dc; break; // Haut
			case 1: dl = 1;newligP = ligP + dl ; newcolP = colP + dc;  break; // Bas
			case 2: dc = 1; newligP = ligP + dl ; newcolP = colP + dc; break; // Droite
			case 3: dc = -1;newligP = ligP + dl ; newcolP = colP + dc; break; // Gauche
		}
		
		switch(direction) {
			case 0 : // haut
				if(ligP < 0 ) {
					//System.out.println("impossible de monter");
					return;}
				else {
					switch(this.niveauActuel.cases[newligP][newcolP]) {
						case 0 ://vide
							this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
							this.niveauActuel.cases[newligP][newcolP] = 2 ;
							this.niveauActuel.pousseurL = newligP;
							this.niveauActuel.pousseurC = newcolP;
							break;
						case 1 : //mur
							//System.out.println("mur en haut");
							break;
						case 4 : // caisse 
							if(this.niveauActuel.cases[newligP - 1][newcolP] == 0 ) {
								this.niveauActuel.cases[newligP - 1][newcolP] = 4 ;
								this.niveauActuel.cases[newligP][newcolP] = 2 ;
								this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
								this.niveauActuel.pousseurL = newligP;
								this.niveauActuel.pousseurC = newcolP;
							}else if (this.niveauActuel.cases[newligP -1 ][newcolP ] == 8) {
								this.niveauActuel.cases[newligP -1 ][newcolP] = 12 ;
								this.niveauActuel.cases[newligP][newcolP] = 2 ;
								this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
								this.niveauActuel.pousseurL = newligP;
								this.niveauActuel.pousseurC = newcolP;
							}else {
								//System.out.println("impossible de pusher la caisse");
							}
							break;
						case 8 :
							this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
							this.niveauActuel.cases[newligP][newcolP] = 10 ;
							this.niveauActuel.pousseurL = newligP;
							this.niveauActuel.pousseurC = newcolP;
							break;
						case 12 :
							if(this.niveauActuel.cases[newligP - 1][newcolP] == 0 ) {//suivant est vide 
								this.niveauActuel.cases[newligP - 1][newcolP] = 4 ;
								this.niveauActuel.cases[newligP][newcolP] = 10 ;
								this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
								this.niveauActuel.pousseurL = newligP;
								this.niveauActuel.pousseurC = newcolP;
							}else if (this.niveauActuel.cases[newligP -1 ][newcolP ] == 8) {//suivant est but 
								this.niveauActuel.cases[newligP -1 ][newcolP] = 12 ;
								this.niveauActuel.cases[newligP][newcolP] = 10 ;
								this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
								this.niveauActuel.pousseurL = newligP;
								this.niveauActuel.pousseurC = newcolP;
							}else {
								System.out.println("impossible de pusher la caisse");
							}
							break;	
					}
				}break;
			case 1 : // bas 
				if(newligP >= this.niveauActuel.lignes) {
					//System.out.println("impossible de descendre");
					return;
				}else {
					switch(this.niveauActuel.cases[newligP][newcolP]) {
						case 0 ://vide
							this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
							this.niveauActuel.cases[newligP][newcolP] = 2 ;
							this.niveauActuel.pousseurL = newligP;
							this.niveauActuel.pousseurC = newcolP;
							break;
						case 1 : //mur
							//System.out.println("mur en bas");
							break;
						case 4 : // caisse 
							if(this.niveauActuel.cases[newligP + 1][newcolP] == 0 ) {
								this.niveauActuel.cases[newligP + 1][newcolP] = 4 ;
								this.niveauActuel.cases[newligP][newcolP] = 2 ;
								this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
								this.niveauActuel.pousseurL = newligP;
								this.niveauActuel.pousseurC = newcolP;
							}else if (this.niveauActuel.cases[newligP +1][newcolP ] == 8) {
								this.niveauActuel.cases[newligP +1 ][newcolP] = 12 ;
								this.niveauActuel.cases[newligP][newcolP] = 2 ;
								this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
								this.niveauActuel.pousseurL = newligP;
								this.niveauActuel.pousseurC = newcolP;
							}else {
								//System.out.println("impossible de pusher la caisse");
							}
							break;
						case 8 :
							this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
							this.niveauActuel.cases[newligP][newcolP] = 10 ;
							this.niveauActuel.pousseurL = newligP;
							this.niveauActuel.pousseurC = newcolP;
							break;
							
						case 12 :
							if(this.niveauActuel.cases[newligP + 1][newcolP] == 0 ) {//suivant est vide 
								this.niveauActuel.cases[newligP + 1][newcolP] = 4 ;
								this.niveauActuel.cases[newligP][newcolP] = 10 ;
								this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
								this.niveauActuel.pousseurL = newligP;
								this.niveauActuel.pousseurC = newcolP;
							}else if (this.niveauActuel.cases[newligP + 1 ][newcolP ] == 8) {//suivant est but 
								this.niveauActuel.cases[newligP +1 ][newcolP] = 12 ;
								this.niveauActuel.cases[newligP][newcolP] = 10 ;
								this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
								this.niveauActuel.pousseurL = newligP;
								this.niveauActuel.pousseurC = newcolP;
							}else {
								System.out.println("impossible de pusher la caisse");
							}
							break;
					}
					
				}break;
			case 2 : //droite 
				if(newcolP >= this.niveauActuel.colonnes) {
					//System.out.println("impossible de bouger a droite");
					return;
				}else {
					switch(this.niveauActuel.cases[newligP][newcolP]) {
						case 0 ://vide
							this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
							this.niveauActuel.cases[newligP][newcolP] = 2 ;
							this.niveauActuel.pousseurL = newligP;
							this.niveauActuel.pousseurC = newcolP;
							break;
						case 1 : //mur
							//System.out.println("mur a droite");
							break;
						case 4 : // caisse 
							if(this.niveauActuel.cases[newligP ][newcolP + 1] == 0 ) {
								this.niveauActuel.cases[newligP ][newcolP + 1] = 4 ;
								this.niveauActuel.cases[newligP][newcolP] = 2 ;
								this.niveauActuel.cases[ligP][colP] = 0 ;
								this.niveauActuel.pousseurL = newligP;
								this.niveauActuel.pousseurC = newcolP;
							}else if (this.niveauActuel.cases[newligP ][newcolP + 1] == 8) {
								this.niveauActuel.cases[newligP ][newcolP + 1] = 12 ;
								this.niveauActuel.cases[newligP][newcolP] = 2 ;
								this.niveauActuel.cases[ligP][colP] = 0 ;
								this.niveauActuel.pousseurL = newligP;
								this.niveauActuel.pousseurC = newcolP;
							}else {
								//System.out.println("impossible de pusher la caisse");
							}
							break;
							
						case 8 :
							this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
							this.niveauActuel.cases[newligP][newcolP] = 10 ;
							this.niveauActuel.pousseurL = newligP;
							this.niveauActuel.pousseurC = newcolP;
							break;
						case 12 :
							if(this.niveauActuel.cases[newligP ][newcolP +1] == 0 ) {//suivant est vide 
								this.niveauActuel.cases[newligP ][newcolP +1] = 4 ;
								this.niveauActuel.cases[newligP][newcolP] = 10 ;
								this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
								this.niveauActuel.pousseurL = newligP;
								this.niveauActuel.pousseurC = newcolP;
							}else if (this.niveauActuel.cases[newligP ][newcolP +1 ] == 8) {//suivant est but 
								this.niveauActuel.cases[newligP  ][newcolP +1] = 12 ;
								this.niveauActuel.cases[newligP][newcolP] = 10 ;
								this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
								this.niveauActuel.pousseurL = newligP;
								this.niveauActuel.pousseurC = newcolP;
							}else {
								//System.out.println("impossible de pusher la caisse");
							}
							break;}				
				}break;
			case 3 : // gauche 
				if(newcolP < 0 ) {
					//System.out.println("impossible de bouger a gauche");
				}else {
					switch(this.niveauActuel.cases[newligP][newcolP]) {
						case 0 ://vide
							this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
							this.niveauActuel.cases[newligP][newcolP] = 2 ;
							this.niveauActuel.pousseurL = newligP;
							this.niveauActuel.pousseurC = newcolP;
							break;
						case 1 : //mur
							//System.out.println("mur a gauche");
							break;
						case 4 : // caisse 
							if(this.niveauActuel.cases[newligP ][newcolP - 1] == 0 ) {
								this.niveauActuel.cases[newligP ][newcolP - 1 ] = 4 ;
								this.niveauActuel.cases[newligP][newcolP] = 2 ;
								this.niveauActuel.cases[ligP][colP] = 0 ;
								this.niveauActuel.pousseurL = newligP;
								this.niveauActuel.pousseurC = newcolP;
							}else if (this.niveauActuel.cases[newligP ][newcolP - 1] == 8) {
								this.niveauActuel.cases[newligP ][newcolP - 1] = 12 ;
								this.niveauActuel.cases[newligP][newcolP] = 2 ;
								this.niveauActuel.cases[ligP][colP] = 0 ;
								this.niveauActuel.pousseurL = newligP;
								this.niveauActuel.pousseurC = newcolP;
							}else {
								//System.out.println("impossible de pusher la caisse");
							}
							break;
						case 8 :
							this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
							this.niveauActuel.cases[newligP][newcolP] = 10 ;
							this.niveauActuel.pousseurL = newligP;
							this.niveauActuel.pousseurC = newcolP;
							break;
						case 12 :
							if(this.niveauActuel.cases[newligP ][newcolP  - 1] == 0 ) {//suivant est vide 
								this.niveauActuel.cases[newligP ][newcolP  - 1] = 4 ;
								this.niveauActuel.cases[newligP][newcolP] = 10 ;
								this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
								this.niveauActuel.pousseurL = newligP;
								this.niveauActuel.pousseurC = newcolP;
							}else if (this.niveauActuel.cases[newligP  ][newcolP  - 1 ] == 8) {//suivant est but 
								this.niveauActuel.cases[newligP  ][newcolP  - 1] = 12 ;
								this.niveauActuel.cases[newligP][newcolP] = 10 ;
								this.niveauActuel.cases[ligP][colP] = (this.niveauActuel.cases[ligP][colP] == 10) ? 8 : 0;
								this.niveauActuel.pousseurL = newligP;
								this.niveauActuel.pousseurC = newcolP;
							}else {
								//System.out.println("impossible de pusher la caisse");
							}
							break;}		
				}break;
							
		}
	}

	
	public boolean estTerminer() {
		//System.out.println("size de casesButs " + this.niveauActuel.CasesBut.size());
		for(Position pos : this.niveauActuel.CasesBut) {
			if(this.niveauActuel.cases[pos.lig][pos.col] != this.niveauActuel.ButCaisse) {
				return false;
			}
		}
		return true;
		
	}
	
	// Getters & setters
	public Niveau getNiveauActuel() {
		return niveauActuel;
	}


	public void setNiveauActuel(Niveau niveauActuel) {
		this.niveauActuel = niveauActuel;
	}


	public LectureNiveau getLec() {
		return lec;
	}


	public void setLec(LectureNiveau lec) {
		this.lec = lec;
	}


}
