package com.monapp.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@PrimaryKeyJoinColumn(name="Appartement_id")
public class Appartement extends Logement {

	@JsonView (Views.Common.class)
	private int numero;

	@JsonView (Views.Common.class)
	private int etage;
	
	
	@JsonView (Views.AppartementWithImmeuble.class)
	@ManyToOne (optional = false)
	private Immeuble immeuble;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getEtage() {
		return etage;
	}

	public void setEtage(int etage) {
		this.etage = etage;
	}

	public Appartement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Immeuble getImmeuble() {
		return immeuble;
	}

	public void setImmeuble(Immeuble immeuble) {
		this.immeuble = immeuble;
	}
	

}
