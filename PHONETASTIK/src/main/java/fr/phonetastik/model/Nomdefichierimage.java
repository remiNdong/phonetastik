package fr.phonetastik.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Nomdefichierimage {

	public Nomdefichierimage() {

	}

	public Nomdefichierimage(String filename) {
		this.filename = filename;
	}

	@Id
	@NotNull
	private String filename;

	public String getFilename() {
		return filename;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Nomdefichierimage)) {
			return false;
		}
		Nomdefichierimage autre = (Nomdefichierimage) obj;

		return this.getFilename().equals(autre.getFilename());
	}

}
