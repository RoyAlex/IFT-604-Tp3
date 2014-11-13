package com.tp1.dto;

import java.io.Serializable;
import java.sql.Time;

public class PenaliteDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int idPenalite;
    private Time timeOfPenalite;
    private int duree;
    private JoueurDTO joueur;
    private MatchDTO match;
    private EquipeDTO equipe;
    
    public PenaliteDTO() {
		super();
	}
    
    public PenaliteDTO(int idPenalite, Time timeOfPenalite, int duree,
			JoueurDTO joueur, MatchDTO match, EquipeDTO equipe) {
		super();
		this.idPenalite = idPenalite;
		this.timeOfPenalite = timeOfPenalite;
		this.duree = duree;
		this.joueur = joueur;
		this.match = match;
		this.equipe = equipe;
	}

	public int getIdPenalite() {
        return idPenalite;
    }

    public void setIdPenalite(int idPenalite) {
        this.idPenalite = idPenalite;
    }
    
    public int getDuree() {
        return duree;
    }
    
    public void setDuree(int duree) {
        this.duree = duree;
    }

    public JoueurDTO getJoueur() {
        return joueur;
    }

    public void setJoueur(JoueurDTO joueur) {
        this.joueur = joueur;
    }
    
    public Time getTimeOfPenalite() {
        return timeOfPenalite;
    }

    public void setTimeOfPenalite(Time timeOfPenalite) {
        this.timeOfPenalite = timeOfPenalite;
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
