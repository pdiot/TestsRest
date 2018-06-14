package com.monapp.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@PrimaryKeyJoinColumn(name="Maison_id")
public class Maison extends Logement {
	
	@JsonView (Views.Common.class)
	private String adresse;

	@JsonView (Views.Common.class)
	private int codePostal;

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

	public Maison() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
