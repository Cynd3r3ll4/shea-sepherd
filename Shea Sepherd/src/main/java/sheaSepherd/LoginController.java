package sheaSepherd;

import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class LoginController implements Serializable {
	
    @Inject
    private BenutzerDAO benutzerDAO;
    
	private String benutzername;
	private Benutzer benutzer;
	private String passwort;
	
	
	public String login() {
		Benutzer gefundenerBenutzer = benutzerDAO.benutzerFinden(benutzername, passwort);
		this.benutzer = gefundenerBenutzer;
		return "funktionen?faces-redirect=true";
	}

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public Benutzer getBenutzer() {
		return this.benutzer;
	}

	public void setBenutzer(Benutzer benutzer) {
		this.benutzer = benutzer;
	}
	
	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	
	
	public void postValidateBenutzername (ComponentSystemEvent ev) throws AbortProcessingException {
		UIInput temp = (UIInput)ev.getComponent();
		this.benutzername = (String)temp.getValue();
	}
	
	public void validateLogin (FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Benutzer temp = benutzerDAO.benutzerFinden(benutzername, (String) value);
        if (temp != null) {
            return; // Benutzername + Passwort stimmen überein
        }
        throw new ValidatorException(new FacesMessage("Der eingegebene Benutzername oder das Passwort sind falsch."));
    }

}
