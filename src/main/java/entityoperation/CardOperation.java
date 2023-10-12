package entityoperation;

import entity.Card;
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

//        insertNewCards();
    }

    public void insertNewCards() {
        Card card = new Card();
        card.setStrength(100);
        card.setCard_name("Test Warrior");

        Card card2 = new Card();
        card2.setStrength(150);
        card2.setCard_name("Test Warrior 2");

        em.getTransaction().begin();
        em.persist(card);
        em.persist(card2);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public List getAllCards() {
        return em.createQuery("Select c from Card c")
                .getResultList();
    }

    public Card getCardById(int cardId) {
        return (Card) em.createQuery("Select c from Card c where c.id = :cid")
                .setParameter("cid", cardId)
                .getSingleResult();
    }
}
