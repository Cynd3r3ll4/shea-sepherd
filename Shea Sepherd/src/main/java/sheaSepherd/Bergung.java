package sheaSepherd;

import jakarta.persistence.*;

@Entity
public class Bergung {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Benutzer benutzer;

    @ManyToOne
    private Netz netz;

    
    public int getId() {
        return id;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public Netz getNetz() {
        return netz;
    }

    public void setNetz(Netz netz) {
        this.netz = netz;
    }
    
    
    public Bergung() {
    	
    }

    public Bergung(Benutzer benutzer, Netz netz) {
        this.benutzer = benutzer;
        this.netz = netz;
    }

}
