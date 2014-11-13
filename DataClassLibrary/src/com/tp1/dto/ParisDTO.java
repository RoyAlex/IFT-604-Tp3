package com.tp1.dto;


import java.io.Serializable;

public class ParisDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idParis;
	private String idUtilisateur;
	private float montant;
	private boolean estEnregistrer;
	private EquipeDTO equipe;
	private MatchDTO match;
	
	public ParisDTO() {
		super();
	}
	
	public ParisDTO(int idParis, String idUtilisateur, float montant,
			boolean estEnregistrer, EquipeDTO equipe, MatchDTO match) {
		super();
		this.idParis = idParis;
		this.idUtilisateur = idUtilisateur;
		this.montant = montant;
		this.estEnregistrer = estEnregistrer;
		this.equipe = equipe;
		this.match = match;
	}

	public EquipeDTO getEquipe() {
		return equipe;
	}

	public void setEquipe(EquipeDTO equipe) {
		this.equipe = equipe;
	}

	public MatchDTO getMatch() {
		return match;
	}

	public void setMatch(MatchDTO match) {
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
