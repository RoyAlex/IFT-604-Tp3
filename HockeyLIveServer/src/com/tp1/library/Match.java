package com.tp1.library;


import java.io.Serializable;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Match implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private int idMatch;
	private int pointageEquipeLocal;
	private int pointageEquipeVisiteur;
	private Time matchTime;
	private int nbrPenalite;
	private String periodeCourante;
	
	public Match() {
		super();
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "match", fetch = FetchType.EAGER)
	private Set<Paris> listeParis = new HashSet<Paris>(0);
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Equipe equipeLocal;

	@ManyToOne(cascade = CascadeType.ALL)
	private Equipe equipeVisiteur;
	
	public Equipe getEquipeLocal() {
		return equipeLocal;
	}

	public void setEquipeLocal(Equipe equipeLocal) {
		this.equipeLocal = equipeLocal;
	}

	public Equipe getEquipeVisiteur() {
		return equipeVisiteur;
	}

	public void setEquipeVisiteur(Equipe equipeVisiteur) {
		this.equipeVisiteur = equipeVisiteur;
	}

	public int getIdMatch() {
		return idMatch;
	}

	public void setIdMatch(int idMatch) {
		this.idMatch = idMatch;
	}

	public int getPointageEquipeLocal() {
		return pointageEquipeLocal;
	}

	public void setPointageEquipeLocal(int pointageEquipeLocal) {
		this.pointageEquipeLocal = pointageEquipeLocal;
	}

	public int getPointageEquipeVisiteur() {
		return pointageEquipeVisiteur;
	}

	public void setPointageEquipeVisiteur(int pointageEquipeVisiteur) {
		this.pointageEquipeVisiteur = pointageEquipeVisiteur;
	}

	public Time getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(Time matchTime) {
		this.matchTime = matchTime;
	}

	public int getNbrPenalite() {
		return nbrPenalite;
	}

	public void setNbrPenalite(int nbrPenalite) {
		this.nbrPenalite = nbrPenalite;
	}

    public String getPeriodeCourante() {
        return periodeCourante;
    }

    public void setPeriodeCourante(String periodeCourante) {
        this.periodeCourante = periodeCourante;
    }

	public Set<Paris> getListeParis() {
		return listeParis;
	}

	public void setListeParis(Set<Paris> listeParis) {
		this.listeParis = listeParis;
	}
}
