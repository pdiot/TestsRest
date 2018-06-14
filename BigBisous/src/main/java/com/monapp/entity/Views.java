package com.monapp.entity;

public interface Views {
	public static interface Common {}
	
	public static interface Logement extends Common {}
	public static interface LogementWithClient extends Logement {}
	public static interface LogementWithAll extends Logement, MaisonWithClient, AppartementWithAll {}
	
	public static interface Maison extends Logement {}
	public static interface MaisonWithClient extends Maison, LogementWithClient {}
	
	public static interface Appartement extends Logement {}
	public static interface AppartementWithImmeuble extends Appartement{}
	public static interface AppartementWithAll extends AppartementWithImmeuble, LogementWithClient {}

	public static interface Immeuble extends Common {}
	public static interface ImmeubleWithAppartement extends Immeuble, LogementWithClient {}

	public static interface Client extends Common {}
	public static interface ClientWithLogement extends Client, AppartementWithImmeuble {}
	
	
	
}
