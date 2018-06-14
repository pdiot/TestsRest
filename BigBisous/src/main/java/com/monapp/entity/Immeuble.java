package com.monapp.entity;

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
public class Immeuble {

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	private int id;

	@JsonView(Views.Common.class)
	private String adresse;

	@JsonView(Views.Common.class)
	private int codePostal;

	@JsonView(Views.ImmeubleWithAppartement.class)
	@OneToMany (mappedBy="immeuble", fetch=FetchType.EAGER)
	private Set<Appartement> appartements;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public Set<Appartement> getAppartements() {
		return appartements;
	}

	public void setAppartements(Set<Appartement> appartements) {
		this.appartements = appartements;
	}
	
	public void ajouterAppartement(Appartement appt) {
		this.appartements.add(appt);
		appt.setImmeuble(this);
	}

	public Immeuble() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
