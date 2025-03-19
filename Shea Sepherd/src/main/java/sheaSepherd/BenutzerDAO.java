package sheaSepherd;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

@ApplicationScoped
public class BenutzerDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("sheaSepherdPersistenceUnit");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    

    public List<Benutzer> getAlleBenutzer() {
    	EntityManager em = getEntityManager();
    	List<Benutzer> benutzerListe = em.createQuery("SELECT b FROM Benutzer b", Benutzer.class).getResultList();
    	em.close();
    	return benutzerListe;
    }

    public Benutzer benutzerFinden(String benutzername, String passwort) {
    	EntityManager em = getEntityManager();
    	Benutzer benutzer = em.createQuery(
                "SELECT b FROM Benutzer b WHERE b.benutzername = :benutzername AND b.passwort = :passwort",
                Benutzer.class)
                .setParameter("benutzername", benutzername)
                .setParameter("passwort", passwort)
                .getSingleResult();
        em.close();
        return benutzer;
    }
    
    public Benutzer benutzerFindenOhnePW(String benutzername) {
        EntityManager em = getEntityManager();
        Benutzer benutzer =em.createQuery(
                    "SELECT b FROM Benutzer b WHERE b.benutzername = :benutzername",
                    Benutzer.class)
                    .setParameter("benutzername", benutzername)
                    .getSingleResult();
        em.close();
        return benutzer;
    }
    
}

