package entityoperation;

import entity.Card;
import entity.PlayerCards;
import jakarta.persistence.*;

import java.util.List;

public class PlayerCardsOperation {

    private EntityManagerFactory emf;
    private EntityManager em;

    public PlayerCardsOperation() {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    public List<PlayerCards> getAllPlayerCards(Long playerId) {
        return em.createQuery("Select pc from PlayerCards pc where pc.player_id = :pid")
                .setParameter("pid", playerId)
                .getResultList();
    }

    public int getPlayerCardsQuantity(String name) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("Card_Game.cards_quantity");

        spq.registerStoredProcedureParameter("player_name", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("quantity", Integer.class, ParameterMode.OUT);

        spq.setParameter("player_name", name);

        boolean result = spq.execute();

        int cards_quantity = (Integer) spq.getOutputParameterValue(2);

        return cards_quantity;
    }

    public int getAveragePlayerCardsStrength(String name) {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("Card_Game.player_avg_strength");

        spq.registerStoredProcedureParameter("player_name", String.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("avg_strength", Integer.class, ParameterMode.OUT);

        spq.setParameter("player_name", name);

        boolean result = spq.execute();

        int avg_strength = (Integer) spq.getOutputParameterValue(2);

        return avg_strength;
    }
}