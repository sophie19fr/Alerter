package com.devises;

public class Amis {

	private String prenom;
	private String nom;
	private String email;
	private String numero;

	public Amis(String prenom, String nom, String email, String numero) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.numero = numero;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Amis [prenom=" + prenom + ", nom=" + nom + ", email=" + email
				+ ", numero=" + numero + "]";
	}

}
