package fr.phonetastik.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
public class Marque {
	
	public Marque() {
		this.visible="TRUE";
	}
	
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotNull
	private String nom;
	
	@Column
	@NotNull
	private String visible;

	public Long getId() {
		return id;
	}

	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}
	
	public Boolean isVisible() {
		return Boolean.parseBoolean(visible.toLowerCase());
	}
	
	

}
