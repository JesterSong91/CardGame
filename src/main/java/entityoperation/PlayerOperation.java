package entityoperation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class PlayerOperation {

    private EntityManagerFactory emf;
    private EntityManager em;

    public PlayerOperation() {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();

//        insertNewPlayer();
    }

    public void insertNewPlayer() {

    }

    public List findAllPlayers() {
        return em.createQuery("Select p from Player p")
                .getResultList();
    }
}
