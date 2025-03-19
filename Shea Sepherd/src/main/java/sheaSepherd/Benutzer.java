package sheaSepherd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Benutzer implements Serializable {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int nr;
	private String name;
	private String vorname;
	private String benutzername;
	private String passwort;
	private String telefonnummer;
	
	@OneToMany(mappedBy = "benutzer", cascade = CascadeType.ALL)
	private List<Bergung> bergungen = new ArrayList<>();
	
	
	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getBenutzername() {
		return benutzername;
	}
	
	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}
	
	public String getPasswort() {
		return passwort;
	}
	
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	public String getTelefonnummer() {
		return telefonnummer;
	}

	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}
	
	public List<Bergung> getBergungen() {
	    return bergungen;
	}

	public void setBergungen(List<Bergung> bergungen) {
	    this.bergungen = bergungen;
	}
	
	public Benutzer(int nr, String name, String vorname, String benutzername, String passwort) {
		super();
		this.nr = nr;
		this.name = name;
		this.vorname = vorname;
		this.benutzername = benutzername;
		this.passwort = passwort;
	}
	
	public Benutzer(String benutzername, String passwort) {
		super();
		this.benutzername = benutzername;
		this.passwort = passwort;
	}
	
	public Benutzer() {
		super();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Benutzer) {
			Benutzer b = (Benutzer)obj;
			if(b.getBenutzername().equals(this.benutzername) && b.getPasswort().equals(this.passwort)) {
				return true;
			}
		}
		return false;
	}

	
}
