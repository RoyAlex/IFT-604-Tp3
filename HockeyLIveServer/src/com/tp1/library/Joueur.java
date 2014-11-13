package com.tp1.library;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Joueur implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private int idJoueur;
	private String nomJoueur;
	private boolean enPenalite;

	public Joueur() {
		super();
	}

	@ManyToOne(cascade = CascadeType.ALL)
	private Equipe equipe;
	
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public int getIdJoueur() {
		return idJoueur;
	}
	
	public void setIdJoueur(int idJoueur) {
		this.idJoueur = idJoueur;
	}
	
	public String getNomJoueur() {
		return nomJoueur;
	}
	
	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}
	
	public boolean isEnPenalite() {
		return enPenalite;
	}
	
	public void setEnPenalite(boolean enPenalite) {
		this.enPenalite = enPenalite;
	}
}
