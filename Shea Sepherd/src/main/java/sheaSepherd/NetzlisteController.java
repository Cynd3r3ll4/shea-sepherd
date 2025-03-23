package sheaSepherd;

import java.io.Serializable;
import java.util.List;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class NetzlisteController implements Serializable{

	@Inject
	private NetzDAO netzDAO;
	
	@Inject
	private BergungDAO bergungDAO;
	
	@Inject
	private LoginController loginc;
	
	@Inject
	private NavigationController navi;
	
	@Inject
	private BenutzerDAO benutzerDAO;

	
    private Netz neuesNetz = new Netz();
    
    
    public Netz getNeuesNetz() {
        return neuesNetz;
    }

    public void setNeuesNetz(Netz neuesNetz) {
        this.neuesNetz = neuesNetz;
    }

	public BergungDAO getBergungDAO() {
		return bergungDAO;
	}

	public void setBergungDAO(BergungDAO bergungDAO) {
		this.bergungDAO = bergungDAO;
	}

	public LoginController getLoginc() {
		return loginc;
	}

	public void setLoginc(LoginController loginc) {
		this.loginc = loginc;
	}
	
	
	public String netzMelden() {
		neuesNetz.setStatus(Status.GEMELDET);
        netzDAO.netzSpeichern(neuesNetz);
        neuesNetz = new Netz(); 
        return navi.netzGemeldet();
	}
	
	public String netzMeldenLogin() {
		neuesNetz.setStatus(Status.GEMELDET);
        netzDAO.netzSpeichern(neuesNetz);
        neuesNetz = new Netz(); 
        return navi.funktionen();
	}
	
	public String bergungAnmelden(Netz netz) {
		Benutzer aktuellerBenutzer = benutzerDAO.benutzerFindenOhnePW(loginc.getBenutzer().getBenutzername());
		netz = netzDAO.findeNetz(netz.getNr());
		netz = netzDAO.netzAktualisieren(netz);
		Bergung bergung = new Bergung(aktuellerBenutzer, netz);
        bergungDAO.bergungSpeichern(bergung);
		netz.setStatus(Status.BERGUNG);
		netzDAO.netzAktualisieren(netz);
        return navi.aufträge();
    }
	
	public void netzGeborgen(Netz netz) {
		netz = netzDAO.findeNetz(netz.getNr());
        netz.setStatus(Status.GEBORGEN);
        netzDAO.netzAktualisieren(netz);
        bergungDAO.bergungLoeschen(netz);
    }
	
	public void netzVerschollen(Netz netz) {
		netz = netzDAO.findeNetz(netz.getNr());
	    netz.setStatus(Status.VERSCHOLLEN);
	    netzDAO.netzAktualisieren(netz);
	    bergungDAO.bergungLoeschen(netz);
	}
	
	public boolean statusGemeldet(Netz netz) {
		return netz.getStatus() == Status.GEMELDET;
	}
	
	public List<Netz> getNetzliste() {
		return netzDAO.getNetze();
	}
	
	public List<Netz> getNetzeZurBergung() {
		return bergungDAO.getNetzeZurBergungBenutzer(loginc.getBenutzer());
	}

}
