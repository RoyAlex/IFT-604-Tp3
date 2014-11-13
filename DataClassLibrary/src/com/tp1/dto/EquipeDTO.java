package com.tp1.dto;

import java.io.Serializable;
import java.util.Set;

public class EquipeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idEquipe;
	private String nomEquipe;
	private Set<JoueurDTO> listeJoueurs;
	
	public EquipeDTO() {
		super();
	}
		
	public EquipeDTO(int idEquipe, String nomEquipe, Set<JoueurDTO> listeJoueurs) {
		super();
		this.idEquipe = idEquipe;
		this.nomEquipe = nomEquipe;
		this.listeJoueurs = listeJoueurs;
	}

	public EquipeDTO(String nomEquipe)
	{
		this.nomEquipe = nomEquipe;
	}
		
	public int getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(int idEquipe) {
		this.idEquipe = idEquipe;
	}

	public String getNomEquipe() {
		return nomEquipe;
	}

	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}

	public Set<JoueurDTO> getListeJoueurs() {
		return listeJoueurs;
	}

	public void setListeJoueurs(Set<JoueurDTO> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}
}
