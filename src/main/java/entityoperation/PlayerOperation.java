package entityoperation;

import entity.Player;
import entity.PlayerCards;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class PlayerOperation {

    private EntityManagerFactory emf;
    private EntityManager em;

    private Player player;

    private Long PlayerCardId;
    private List<PlayerCards> playerCards;
    private String name;

    private StoredProcedureQuery spq;

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

    public void playCard(Long PlayerCardId) {
        spq = em.createStoredProcedureQuery("Card_Game.play_card");

        spq.registerStoredProcedureParameter("playing_card_id", Long.class, ParameterMode.IN);

        spq.setParameter("playing_card_id", PlayerCardId);

        spq.execute();
    }

    public Player getPlayer(String name) {
        return (Player) em.createQuery("Select p from Player p where p.name = :pn")
                .setParameter("pn", name)
                .getResultList().get(0);
    }
}
