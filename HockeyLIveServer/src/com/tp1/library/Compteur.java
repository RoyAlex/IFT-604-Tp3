package com.tp1.library;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Compteur implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
    private int idCompteur;
    private Time timeOfGoal;
	
	public Compteur() 
	{
		super();
	}

	@ManyToOne(cascade = CascadeType.ALL)
    private Joueur joueur;
	
	@ManyToOne(cascade = CascadeType.ALL)
    private Match match;
	
	@ManyToOne(cascade = CascadeType.ALL)
    private Equipe equipe;
	
	// Get, Set
	
	public int getIdCompteur() {
        return idCompteur;
    }

    public void setIdCompteur(int idCompteur) {
        this.idCompteur = idCompteur;
    }

    public Time getTimeOfGoal() {
        return timeOfGoal;
    }

    public void setTimeOfGoal(Time timeOfGoal) {
        this.timeOfGoal = timeOfGoal;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
}
