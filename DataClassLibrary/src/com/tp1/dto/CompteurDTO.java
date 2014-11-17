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
    private int periode;
    private boolean dejaAfficher;
    
    public CompteurDTO() {
		super();
	}
    	
	public CompteurDTO(int idCompteur, Time timeOfGoal, JoueurDTO joueur,
			MatchDTO match, EquipeDTO equipe, int periode) {
		super();
		this.idCompteur = idCompteur;
		this.timeOfGoal = timeOfGoal;
		this.joueur = joueur;
		this.match = match;
		this.equipe = equipe;
		this.setPeriode(periode);
		this.setDejaAfficher(false);
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

    public int getPeriode() {
        return periode;
    }

    public void setPeriode(int periode) {
        this.periode = periode;
    }

    public boolean isDejaAfficher() {
        return dejaAfficher;
    }

    public void setDejaAfficher(boolean dejaAfficher) {
        this.dejaAfficher = dejaAfficher;
    }
}
