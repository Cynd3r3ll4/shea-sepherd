package sheaSepherd;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

@ApplicationScoped
public class BergungDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("sheaSepherdPersistenceUnit");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void bergungSpeichern(Bergung bergung) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        bergung.setNetz(em.merge(bergung.getNetz()));
        em.persist(bergung);
        em.getTransaction().commit();
        em.close();
    }
    
    public void bergungLoeschen(Netz netz) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Bergung b WHERE b.netz = :netz")
          .setParameter("netz", netz)
          .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public List<Netz> getNetzeZurBergungBenutzer(Benutzer benutzer) {
    	EntityManager em = getEntityManager();
        List<Netz> netze = em.createQuery(
                "SELECT b.netz FROM Bergung b WHERE b.benutzer = :benutzer AND b.netz.status = :status", Netz.class)
                .setParameter("benutzer", benutzer)
                .setParameter("status", Status.BERGUNG)
                .getResultList();
        em.close();
        return netze;
    }
}
