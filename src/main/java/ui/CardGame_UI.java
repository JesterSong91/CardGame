package ui;

import entity.Card;
import entity.Player;
import entityoperation.CardOperation;
import entityoperation.PlayerOperation;

import javax.swing.*;
import java.util.List;

public class CardGame_UI {
    public CardOperation co;
    public PlayerOperation po;

    public JPanel rootPanel;

    public CardGame_UI() {
        co = new CardOperation();
        po = new PlayerOperation();

        initButtonsActionListeners();

        List<Card> cards = co.findAllCards();

        for (Card currCard : cards
             ) {
            System.out.println("currCard: " + currCard.getId() + ", " + currCard.getStrength() + ", " + currCard.getCard_name());
        }

        List<Player> players = po.findAllPlayers();

        for (Player currPlayer : players
             ) {
            System.out.println("currPlayer: " + currPlayer.getId() + ", " + currPlayer.getName());
        }
    }

    public void initButtonsActionListeners() {
    }

}
