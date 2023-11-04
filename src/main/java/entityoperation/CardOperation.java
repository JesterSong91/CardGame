package entityoperation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class CardOperation {

    private EntityManagerFactory emf;
    private EntityManager em;

    public CardOperation() {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    public List getAllCards() {
        return em.createQuery("Select c from Card c")
                .getResultList();
    }
}
