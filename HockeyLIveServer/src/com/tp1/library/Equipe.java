package com.tp1.library;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Equipe implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private int idEquipe;
	private String nomEquipe;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "equipe", fetch = FetchType.EAGER)
	private Set<Joueur> listeJoueurs = new HashSet<Joueur>(0);
	
	/*@OneToMany(cascade = CascadeType.ALL, mappedBy = "equipeLocal")
	private Set<Match> listeMatchsLocal = new HashSet<Match>(0);
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "equipeVisiteur")
	private Set<Match> listeMatchsVisiteur = new HashSet<Match>(0);*/
	


    public Set<Joueur> getListeJoueurs() {
		return listeJoueurs;
	}

	public void setListeJoueurs(Set<Joueur> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}

	public Equipe()
	{
		super();
	}
	
	public Equipe(String nomEquipe)
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
}
