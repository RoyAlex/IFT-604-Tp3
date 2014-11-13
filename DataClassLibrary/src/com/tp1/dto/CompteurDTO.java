package com.tp1.dto;

import java.io.Serializable;
import java.sql.Time;

public class CompteurDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private int idCompteur;
    private Time timeOfGoal;
    private JoueurDTO joueur;
    private MatchDTO match;
    private EquipeDTO equipe;
    
    public CompteurDTO() {
		super();
	}
    	
	public CompteurDTO(int idCompteur, Time timeOfGoal, JoueurDTO joueur,
			MatchDTO match, EquipeDTO equipe) {
		super();
		this.idCompteur = idCompteur;
		this.timeOfGoal = timeOfGoal;
		this.joueur = joueur;
		this.match = match;
		this.equipe = equipe;
	}

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

    public JoueurDTO getJoueur() {
        return joueur;
    }

    public void setJoueur(JoueurDTO joueur) {
        this.joueur = joueur;
    }

    public MatchDTO getMatch() {
        return match;
    }

    public void setMatch(MatchDTO match) {
        this.match = match;
    }

    public EquipeDTO getEquipe() {
        return equipe;
    }

    public void setEquipe(EquipeDTO equipe) {
        this.equipe = equipe;
    }
}
