package ui;

import entity.Card;
import entity.PlayerCards;
import entityoperation.CardOperation;
import entityoperation.PlayerOperation;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDeck {

    public CardOperation co;

    private PlayerOperation po;
    private ArrayList<JPanel> playerCards;
    private JLabel playerScoreLabel;
    private boolean haveToMove;

    private PlayerDeck oppositeDeck;

    public boolean isHaveToMove() {
        return haveToMove;
    }

    public void setHaveToMove(boolean haveToMove) {
        this.haveToMove = haveToMove;
    }

    public PlayerDeck getOppositeDeck() {
        return oppositeDeck;
    }

    public void setOppositeDeck(PlayerDeck oppositeDeck) {
        this.oppositeDeck = oppositeDeck;
    }

    public PlayerDeck(PlayerOperation po, ArrayList<JPanel> playerCards, JLabel playerScoreLabel, boolean haveToMove) {
        co = new CardOperation();

        this.po = po;
        this.playerCards = playerCards;
        this.playerScoreLabel = playerScoreLabel;
        this.haveToMove = haveToMove;

        List<Card> cards = co.getAllCards();

        int i = 0;

        for (PlayerCards currPC : po.getPlayerCards()
        ) {
            for (Card currCard : cards
            ) {
                if (currPC.getCard_id() == currCard.getId()) {
                    JLabel currUnitName = new JLabel(currCard.getCard_name());
                    JLabel currStrength = new JLabel(String.valueOf(currCard.getStrength()));
                    JLabel currPlayerCardId = new JLabel(String.valueOf(currPC.getId()));
                    playerCards.get(i).add(currUnitName);
                    playerCards.get(i).add(currStrength);
                    playerCards.get(i).add(currPlayerCardId);

                    playerCards.get(i).addMouseListener(new CardPanelMouseListener(currPC.getId(), po, playerScoreLabel, this));
                }
            }

            i++;
        }
    }

    public void oppositeDeckMove() {
        haveToMove = !haveToMove;
        oppositeDeck.setHaveToMove(true);
    }
}
