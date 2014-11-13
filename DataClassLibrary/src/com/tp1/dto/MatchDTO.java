package com.tp1.dto;


import java.io.Serializable;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

public class MatchDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idMatch;
	private int pointageEquipeLocal;
	private int pointageEquipeVisiteur;
	private Time matchTime;
	private int nbrPenalite;
	private String periodeCourante;
	private Set<ParisDTO> listeParis = new HashSet<ParisDTO>(0);
	private EquipeDTO equipeLocal;
	private EquipeDTO equipeVisiteur;
	
	public MatchDTO() {
		super();
	}

	public MatchDTO(int idMatch, int pointageEquipeLocal,
			int pointageEquipeVisiteur, Time matchTime, int nbrPenalite,
			String periodeCourante, Set<ParisDTO> listeParis,
			EquipeDTO equipeLocal, EquipeDTO equipeVisiteur) {
		super();
		this.idMatch = idMatch;
		this.pointageEquipeLocal = pointageEquipeLocal;
		this.pointageEquipeVisiteur = pointageEquipeVisiteur;
		this.matchTime = matchTime;
		this.nbrPenalite = nbrPenalite;
		this.periodeCourante = periodeCourante;
		this.listeParis = listeParis;
		this.equipeLocal = equipeLocal;
		this.equipeVisiteur = equipeVisiteur;
	}

	public EquipeDTO getEquipeLocal() {
		return equipeLocal;
	}

	public void setEquipeLocal(EquipeDTO equipeLocal) {
		this.equipeLocal = equipeLocal;
	}

	public EquipeDTO getEquipeVisiteur() {
		return equipeVisiteur;
	}

	public void setEquipeVisiteur(EquipeDTO equipeVisiteur) {
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

	public Set<ParisDTO> getListeParis() {
		return listeParis;
	}

	public void setListeParis(Set<ParisDTO> listeParis) {
		this.listeParis = listeParis;
	}
}
