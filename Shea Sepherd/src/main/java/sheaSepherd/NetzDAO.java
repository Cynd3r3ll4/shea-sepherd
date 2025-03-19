package sheaSepherd;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

@ApplicationScoped
public class NetzDAO {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("sheaSepherdPersistenceUnit");

	
	private EntityManager getEntityManager() {
    	return emf.createEntityManager();
    }

	public void netzSpeichern(Netz netz) {
		EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(netz);
        em.getTransaction().commit();
        em.close();
    }
    
    public Netz netzAktualisieren(Netz netz) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Netz aktNetz = em.merge(netz);
        em.getTransaction().commit();
        em.close();
        return aktNetz;
    }
    
    public Netz findeNetz(int nr) {
        EntityManager em = getEntityManager();
        Netz netz = em.find(Netz.class, nr);
        em.close();
        return netz;
    }

    public List<Netz> getNetze() {
        EntityManager em = getEntityManager();
        List<Netz> netze = em.createQuery("SELECT n FROM Netz n", Netz.class).getResultList();
        em.close();
        return netze;
    }
    
}
