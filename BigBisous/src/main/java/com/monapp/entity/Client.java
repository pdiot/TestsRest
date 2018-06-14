package com.monapp.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Client {
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	private int id;

	@JsonView(Views.Common.class)
	private String nom;

	@JsonView(Views.Common.class)
	private String prenom;

	@JsonView(Views.Common.class)
	private String adresse;
	
	@JsonView(Views.ClientWithLogement.class)
	@OneToMany (mappedBy="client", fetch=FetchType.EAGER)
	private Set<Logement> logements;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Set<Logement> getLogements() {
		return logements;
	}

	public void setLogements(Set<Logement> logements) {
		this.logements = logements;
	}

	public Client() {
		super();
		logements = new HashSet<Logement>();
		// TODO Auto-generated constructor stub
	}
	
	public void ajouterLogement(Logement log) {
		this.logements.add(log);
		log.setClient(this);
	}
	
}
