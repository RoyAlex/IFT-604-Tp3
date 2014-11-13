package com.tp1.dto;

import java.io.Serializable;

public class JoueurDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idJoueur;
	private String nomJoueur;
	private boolean enPenalite;
	private EquipeDTO equipe;
	
	public JoueurDTO() {
		super();
	}
	
	public JoueurDTO(int idJoueur, String nomJoueur, boolean enPenalite,
			EquipeDTO equipe) {
		super();
		this.idJoueur = idJoueur;
		this.nomJoueur = nomJoueur;
		this.enPenalite = enPenalite;
		this.equipe = equipe;
	}

	public EquipeDTO getEquipe() {
		return equipe;
	}

	public void setEquipe(EquipeDTO equipe) {
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
