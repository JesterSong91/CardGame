package entityoperation;

import entity.PlayerCards;
import jakarta.persistence.*;

import java.util.List;

public class PlayerCardsOperation {

    private EntityManagerFactory emf;
    private EntityManager em;
    private StoredProcedureQuery spq;

    private List<PlayerCards> playerCards;

    public PlayerCardsOperation() {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    public List<PlayerCards> getAllPlayerCards(Long playerId) {
        playerCards = em.createQuery("Select pc from PlayerCards pc where pc.player_id = :pid")
                .setParameter("pid", playerId)
                .setMaxResults(6)
                .getResultList();

        return playerCards;
    }

    public int getPlayerCardsQuantity(String name, boolean onlyActiveCards) {
        if (onlyActiveCards) {
            spq = em.createStoredProcedureQuery("Card_Game.available_cards_quantity");
        } else {
            spq = em.createStoredProcedureQuery("Card_Game.all_cards_quantity");
        }

        spq.registerStoredProcedureParameter("player_name", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("quantity", Integer.class, ParameterMode.OUT);

        spq.setParameter("player_name", name);

        spq.execute();

        int cards_quantity = (Integer) spq.getOutputParameterValue(2);

        return cards_quantity;
    }

    public int getAveragePlayerCardsStrength(String name) {
        spq = em.createStoredProcedureQuery("Card_Game.player_avg_strength");

        spq.registerStoredProcedureParameter("player_name", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("avg_strength", Integer.class, ParameterMode.OUT);

        spq.setParameter("player_name", name);

        spq.execute();

        int avg_strength = (Integer) spq.getOutputParameterValue(2);

        return avg_strength;
    }

    public boolean isPlayerHasCards(String name) {
        return true;
    }

    public void resetPlayerCards() {
        spq = em.createStoredProcedureQuery("Card_Game.reset_played_cards");

        spq.execute();
    }
}