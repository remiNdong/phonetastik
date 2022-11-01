package fr.phonetastik.authentification;


import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table //(name="user",schema = "ebdb")
public class User implements Serializable {
	
	public User() {
		admin="FALSE";
	}

	@Id
	private String email;

	@Column(name="pseudo")
	private String pseudo;

	@Column(name="password")
	private String password;
	
	@Column(name="admin")
	private String admin;

	public String getEmail() {
		return email;
	}

	

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	
	public Boolean isAdmin() {
		return Boolean.parseBoolean(admin.toLowerCase());
	}

	public String toString() {

		return "User [email=" + email + ", pseudo=" + pseudo + "]";
	}


	

}