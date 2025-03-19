package sheaSepherd;

import java.io.Serializable;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class NavigationController implements Serializable{
	
	
	public String home() {
        return "index?faces-redirect=true";
    }
	
	public String netzMelden() {
        return "nurMelden?faces-redirect=true";
    }
    
    public String login() {
        return "login?faces-redirect=true";
    }
    
    public String aufträge() {
        return "auftraege?faces-redirect=true";
    }
    
    public String funktionen() {
        return "funktionen?faces-redirect=true";
    }
    
}
