package com.tp1.library;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Penalite implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue
    private int idPenalite;
    private Time timeOfPenalite;
    private int duree;
    private int periode;
    private boolean dejaAfficher;
    
    public Penalite() {
		super();
	}

	@ManyToOne(cascade = CascadeType.ALL)
    private Joueur joueur;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Match match;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Equipe equipe;
    
    public int getIdCompteur() {
        return idPenalite;
    }

    public void setIdCompteur(int idPenalite) {
        this.idPenalite = idPenalite;
    }
    
    public int getDuree() {
        return duree;
    }
    
    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getPeriode() {
        return periode;
    }

    public void setPeriode(int periode) {
        this.periode = periode;
    }

    public Time getTimeOfPenalite() {
        return timeOfPenalite;
    }

    public void setTimeOfPenalite(Time timeOfPenalite) {
        this.timeOfPenalite = timeOfPenalite;
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

    public boolean isDejaAfficher() {
        return dejaAfficher;
    }

    public void setDejaAfficher(boolean dejaAfficher) {
        this.dejaAfficher = dejaAfficher;
    }
}
