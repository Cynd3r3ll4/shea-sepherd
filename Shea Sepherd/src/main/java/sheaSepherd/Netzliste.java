package sheaSepherd;

import java.io.Serializable;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class Netzliste implements Serializable {
	
	@Inject
    private NetzDAO netzDAO;

    private Netz neuesNetz = new Netz();

    public String netzMelden() {
        neuesNetz.setStatus(Status.GEMELDET);
        netzDAO.netzSpeichern(neuesNetz); // Hier wird das Netz in die DB gespeichert
        neuesNetz = new Netz(); // Eingabefelder leeren
        return "nurGemeldet?faces-redirect=true";
    }

    public List<Netz> getNetzliste() {
        return netzDAO.getNetze(); // Holt alle Netze aus der DB
    }

    public Netz getNeuesNetz() {
        return neuesNetz;
    }

    public void setNeuesNetz(Netz neuesNetz) {
        this.neuesNetz = neuesNetz;
    }
    
}
