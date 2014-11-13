package com.tp1.library;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Paris implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private int idParis;
	private String idUtilisateur;
	
	private float montant;
	private boolean estEnregistrer;
	
	public Paris() {
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

	@ManyToOne(cascade = CascadeType.ALL)
	private Match match;

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public int getIdParis() {
		return idParis;
	}

	public void setIdParis(int idParis) {
		this.idParis = idParis;
	}

	public String getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(String idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}
	
	public boolean getEstEnregistrer() {
		return estEnregistrer;
	}

	public void setEstEnregistrer(boolean estEnregistrer) {
		this.estEnregistrer = estEnregistrer;
	}
}
