package entityoperation;

import entity.Player;
import entity.PlayerCards;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class PlayerOperation {

    private EntityManagerFactory emf;
    private EntityManager em;

    private Player player;

    private List<PlayerCards> playerCards;
    private String name;

    public PlayerOperation(String name) {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();

        this.name = name;

        player = (Player) em.createQuery("Select p from Player p where p.name = :pn")
                .setParameter("pn", name)
                .getResultList().get(0);

        playerCards = em.createQuery("Select pc from PlayerCards pc where pc.player_id = :pid")
                .setParameter("pid", player.getId())
                .setMaxResults(6)
                .getResultList();
    }

    public List<PlayerCards> getPlayerCards() {
        return playerCards;
    }

    public Player getPlayer(String name) {
        return (Player) em.createQuery("Select p from Player p where p.name = :pn")
                .setParameter("pn", name)
                .getResultList().get(0);
    }
}
