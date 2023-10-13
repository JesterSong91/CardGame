package ui;

import entity.Card;
import entity.Player;
import entity.PlayerCards;
import entityoperation.CardOperation;
import entityoperation.PlayerCardsOperation;
import entityoperation.PlayerOperation;

import javax.swing.*;
import java.util.List;

public class CardGame_UI {
    public CardOperation co;
    public PlayerOperation po;
    public PlayerCardsOperation pco;

    public JPanel rootPanel;

    public CardGame_UI() {
        co = new CardOperation();
        po = new PlayerOperation();
        pco = new PlayerCardsOperation();

        initButtonsActionListeners();

        List<Card> cards = co.getAllCards();

//        for (Card currCard : cards
//             ) {
//            System.out.println("currCard: " + currCard.getId() + ", " + currCard.getStrength() + ", " + currCard.getCard_name());
//        }

        List<Player> players = po.getAllPlayers();

        for (Player currPlayer : players
             ) {
            System.out.println("currPlayer: " + currPlayer.getId() + ", " + currPlayer.getName());
            System.out.println("Player has " + pco.getPlayerCardsQuantity(currPlayer.getName(), false) + " cards with " + pco.getAveragePlayerCardsStrength(currPlayer.getName()) + " average strength");
            System.out.println("Player has " + pco.getPlayerCardsQuantity(currPlayer.getName(), true) + " active cards");

                List<PlayerCards> pc = pco.getAllPlayerCards(currPlayer.getId());

            System.out.println("    currPlayerCards: ");
            for (PlayerCards currPlayerCard : pc) {
                Card currCard = co.getCardById(currPlayerCard.getCard_id());
                System.out.println("        "  + " " + currCard.getCard_name() + ", " + currCard.getStrength() + ", "
                        + currPlayerCard.getIs_played());
            }

            System.out.println("===============================================");
        }


    }

    public void initButtonsActionListeners() {
    }

}
