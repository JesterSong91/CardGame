package ui;

import entity.Card;
import entityoperation.CardOperation;

import javax.swing.*;
import java.util.List;

public class CardGame_UI {
    public CardOperation co;

    public JPanel rootPanel;

    public CardGame_UI() {
        co = new CardOperation();

        initButtonsActionListeners();

        List<Card> cards = co.findAllCards();

        for (Card currCard : cards
             ) {
            System.out.println("currCard: " + currCard.getId() + ", " + currCard.getStrength() + ", " + currCard.getCard_name());
        }
    }

    public void initButtonsActionListeners() {
    }

}
