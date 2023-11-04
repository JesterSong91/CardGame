package entityoperation;

import entity.Player;
import entity.PlayerCards;
import jakarta.persistence.*;
import util.CardGameConsts;

import java.util.List;

public class PlayerOperation {

    private EntityManagerFactory emf;


    private EntityManager em;
    private EntityManager em2;


    private Player player;

    private Long PlayerCardId;
    private List<PlayerCards> playerCards;
    private String name;
    private String oppositeName;

    private int playerStrength;

    private static int cardsQuantity;

    public int getPlayerStrength() {
        return playerStrength;
    }

    public static int getCardsQuantity() {
        return cardsQuantity;
    }

    private StoredProcedureQuery spq;
//    private StoredProcedureQuery spq2;
//
//    private StoredProcedureQuery spq3;

    public PlayerOperation(String name, String oppositeName) {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        em2 = emf.createEntityManager();

        this.name = name;
        this.oppositeName = oppositeName;
        this.playerStrength = 0;

        cardsQuantity = CardGameConsts.BOTH_PLAYER_CARDS_LIMIT;

        player = (Player) em.createQuery("Select p from Player p where p.name = :pn")
                .setParameter("pn", name)
                .getResultList().get(0);

        playerCards = em.createQuery("Select pc from PlayerCards pc where pc.player_id = :pid")
                .setParameter("pid", player.getId())
                .setMaxResults(6)
                .getResultList();

        markSelectedCards();
    }

    public List<PlayerCards> getPlayerCards() {
        return playerCards;
    }

    public void playCard(Long PlayerCardId) {
        System.out.println("playCard");
        spq = em.createStoredProcedureQuery("Card_Game.play_card");

        spq.registerStoredProcedureParameter("playing_card_id", Long.class, ParameterMode.IN);

        spq.setParameter("playing_card_id", PlayerCardId);

        spq.execute();

        em.close();

        em = emf.createEntityManager();

        int cS = calculateEnemyClockersShield();
        System.out.println("Clockers shield: " + cS);

        int pS = calculatePlayerStrength();
        System.out.println("Player strength only: " + pS);

//        playerStrength = pS;
        playerStrength = pS - cS;

        if (playerStrength < 0)
            playerStrength *= -1;
        System.out.println("Player strength: " + playerStrength);
        System.out.println();

        cardsQuantity--;

        System.out.println("============================");
        System.out.println();
        System.out.println();
    }

    private void markSelectedCards() {
        for (PlayerCards currPC : playerCards) {
            spq = em.createStoredProcedureQuery("Card_Game.mark_card_is_in_deck");

            spq.registerStoredProcedureParameter("playing_card_id", Long.class, ParameterMode.IN);

            spq.setParameter("playing_card_id", currPC.getId());

            spq.execute();
        }
    }

    private int calculateEnemyClockersShield() {
        spq = em.createStoredProcedureQuery("Card_Game.enemy_strength");

        spq.registerStoredProcedureParameter("player_name", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("shield", Integer.class, ParameterMode.OUT);

        spq.setParameter("player_name", oppositeName);

        spq.execute();

        return (Integer) spq.getOutputParameterValue(2);
    }

    private int calculatePlayerStrength() {
        spq = em2.createStoredProcedureQuery("Card_Game.player_strength");

        System.out.println("{{{{");
        System.out.println("    em.createStoredProcedureQuery(\"Card_Game.player_strength\");");

        spq.registerStoredProcedureParameter("player_name", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("strength", Integer.class, ParameterMode.OUT);

        System.out.println("    spq3.registerStoredProcedureParameter");

        spq.setParameter("player_name", player.getName());

        System.out.println("    spq3.setParameter(\"player_name\", player.getName());");

        spq.execute();

        System.out.println("    spq3.execute();");

        int strength = (Integer) spq.getOutputParameterValue("strength");

        System.out.println("    strength: " + strength);
        System.out.println("}}}}");

        return strength;
    }

    public Player getPlayer(String name) {
        return (Player) em.createQuery("Select p from Player p where p.name = :pn")
                .setParameter("pn", name)
                .getResultList().get(0);
    }
}
