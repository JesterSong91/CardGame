package ui;

import entity.Card;
import entity.Player;
import entity.PlayerCards;
import entityoperation.CardOperation;
import entityoperation.PlayerCardsOperation;
import entityoperation.PlayerOperation;
import util.CardGameConsts;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CardGame_UI {
    public CardOperation co;
    public PlayerOperation po;
    public PlayerCardsOperation pco;

    public JPanel rootPanel;
    private JPanel secondPlayerTablePart;
    private JPanel firstPlayerTablePart;
    private JPanel gameTable;
    private JPanel firstFirstCard;
    private JPanel secondFirstCard;
    private JPanel thirdFirstCard;
    private JPanel fourthFirstCard;
    private JPanel fifthFirstCard;
    private JPanel sixthFirstCard;
    private JLabel firstPlayerName;
    private JLabel secondPlayerName;

    public Player firstPlayer;
    public Player secondPlayer;

    private ArrayList<JPanel> firstPlayerCards;
    private ArrayList<JPanel> secondPlayerCards;

    public CardGame_UI() {
        co = new CardOperation();
        po = new PlayerOperation();
        pco = new PlayerCardsOperation();

        initUI();

        initButtonsActionListeners();

        initEntities();
    }

    public void initButtonsActionListeners() {
    }

    public void initUI() {
        firstPlayerTablePart.setBorder(BorderFactory.createLineBorder(Color.black));
        secondPlayerTablePart.setBorder(BorderFactory.createLineBorder(Color.black));
        gameTable.setBorder(BorderFactory.createLineBorder(Color.black));

//        firstPlayerCards = playerCardsPanelFactory();
//        secondPlayerCards = playerCardsPanelFactory();

        System.out.println("firstPlayerTablePart: " + firstPlayerTablePart);

        System.out.println("firstPlayerTablePart : " + firstPlayerTablePart);

//        JLabel labelUnitName = new JLabel("Test Unit");
//        JLabel labelUnitStrength = new JLabel("120");
//
//        firstFirstCard.add(labelUnitName);
//        firstFirstCard.add(labelUnitStrength);

        firstPlayerCards = new ArrayList<>(6);
        firstPlayerCards.add(firstFirstCard);
        firstPlayerCards.add(secondFirstCard);
        firstPlayerCards.add(thirdFirstCard);
        firstPlayerCards.add(fourthFirstCard);
        firstPlayerCards.add(fifthFirstCard);
        firstPlayerCards.add(sixthFirstCard);
    }

    public ArrayList<JPanel> playerCardsPanelFactory() {
        ArrayList<JPanel> cards = new ArrayList<>(CardGameConsts.PLAYER_CARDS_LIMIT);

        for (int i = 0; i < CardGameConsts.PLAYER_CARDS_LIMIT; i++) {
            JPanel currPan = new JPanel();
            JLabel unitName = new JLabel("Test Unit");
            JLabel unitStrength = new JLabel("50");
            currPan.add(unitName);
            currPan.add(unitStrength);
            currPan.setBorder(BorderFactory.createLineBorder(Color.orange));
            cards.add(currPan);
//            System.out.println("Test!");
//            System.out.println("cards.size(): " + cards.size());
//            System.out.println("currPan: " + currPan);
        }

//        System.out.println("cards.size(): " + cards.size());

        return cards;
    }

    public void initEntities() {
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

        firstPlayer = po.getPlayer("John");
        firstPlayerName.setText(firstPlayer.getName());

        secondPlayer = po.getPlayer("Mixy");
        secondPlayerName.setText(secondPlayer.getName());

        List<Card> cards = co.getAllCards();

        for (Card currCard : cards
        ) {
            System.out.println("currCard: " + currCard.getId() + ", " + currCard.getStrength() + ", " + currCard.getCard_name());
        }

        int i = 0;

        for (PlayerCards currPC : pco.getAllPlayerCards(firstPlayer.getId())
             ) {
            for (Card currCard : cards
            ) {
                if (currPC.getCard_id() == currCard.getId()) {
                    JLabel currUnitName = new JLabel(currCard.getCard_name());
                    JLabel currStrength = new JLabel(String.valueOf(currCard.getStrength()));
                    firstPlayerCards.get(i).add(currUnitName);
                    firstPlayerCards.get(i).add(currStrength);
                }
            }

            i++;
        }
    }

}
