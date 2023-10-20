package entityoperation;

import entity.Player;
import entity.PlayerCards;
import jakarta.persistence.*;
import util.CardGameConsts;

import java.util.ArrayList;
import java.util.List;

public class PlayerOperation {

    private EntityManagerFactory emf;
    private EntityManager em;

    private Player player;

    private Long PlayerCardId;
    private List<PlayerCards> playerCards;
    private String name;

    private int playerStrength;

    private static int cardsQuantity;

    public int getPlayerStrength() {
        return playerStrength;
    }

    public static int getCardsQuantity() {
        return cardsQuantity;
    }

    private StoredProcedureQuery spq;

    public PlayerOperation(String name) {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();

        this.name = name;
        this.playerStrength = 0;

        cardsQuantity = CardGameConsts.BOTH_PLAYER_CARDS_LIMIT;

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

        playerStrength = calculatePlayerStrength();
        System.out.println("Player " + player.getName() + " has " + playerStrength + " score.");

        cardsQuantity--;
    }

    private int calculatePlayerStrength() {
        spq = em.createStoredProcedureQuery("Card_Game.player_strength");

        spq.registerStoredProcedureParameter("player_name", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("strength", Integer.class, ParameterMode.OUT);

        spq.setParameter("player_name", player.getName());

        spq.execute();

        return (Integer) spq.getOutputParameterValue(2);
    }

    public Player getPlayer(String name) {
        return (Player) em.createQuery("Select p from Player p where p.name = :pn")
                .setParameter("pn", name)
                .getResultList().get(0);
    }
}
