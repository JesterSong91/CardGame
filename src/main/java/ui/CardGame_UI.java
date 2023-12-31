package ui;

import entity.Player;
import entityoperation.CardOperation;
import entityoperation.PlayerCardsOperation;
import entityoperation.PlayerOperation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CardGame_UI {
    public CardOperation co;
    public PlayerOperation fpo;
    public PlayerOperation spo;
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
    private JPanel firstSecondCard;
    private JPanel secondSecondCard;
    private JPanel thirdSecondCard;
    private JPanel fourthSecondCard;
    private JPanel fifthSecondCard;
    private JPanel sixthSecondCard;
    private JLabel secPlayerScore;
    private JLabel firPlayerScore;

    public Player firstPlayer;
    public Player secondPlayer;

    private ArrayList<JPanel> firstPlayerCards;
    private ArrayList<JPanel> secondPlayerCards;

    public PlayerDeck firstPlayerDeck;
    public PlayerDeck secondPlayerDeck;

    public CardGame_UI() {
        co = new CardOperation();
        fpo = new PlayerOperation("Fash", "Mark");
        spo = new PlayerOperation("Mark", "Fash");
        pco = new PlayerCardsOperation();

        initUI();

        initButtonsActionListeners();

        initDecks();
    }

    public void initButtonsActionListeners() {
    }

    public void initUI() {
        firstPlayerTablePart.setBorder(BorderFactory.createLineBorder(Color.black));
        secondPlayerTablePart.setBorder(BorderFactory.createLineBorder(Color.black));
        gameTable.setBorder(BorderFactory.createLineBorder(Color.black));

        firstPlayerCards = new ArrayList<>(6);
        firstPlayerCards.add(firstFirstCard);
        firstPlayerCards.add(secondFirstCard);
        firstPlayerCards.add(thirdFirstCard);
        firstPlayerCards.add(fourthFirstCard);
        firstPlayerCards.add(fifthFirstCard);
        firstPlayerCards.add(sixthFirstCard);

        secondPlayerCards = new ArrayList<>(6);
        secondPlayerCards.add(firstSecondCard);
        secondPlayerCards.add(secondSecondCard);
        secondPlayerCards.add(thirdSecondCard);
        secondPlayerCards.add(fourthSecondCard);
        secondPlayerCards.add(fifthSecondCard);
        secondPlayerCards.add(sixthSecondCard);
    }

    public void initDecks() {
        firstPlayer = fpo.getPlayer("Fash");
        firstPlayerName.setText(firstPlayer.getName());

        secondPlayer = spo.getPlayer("Mark");
        secondPlayerName.setText(secondPlayer.getName());

        firPlayerScore.setText(String.valueOf(fpo.getPlayerStrength()));
        secPlayerScore.setText(String.valueOf(spo.getPlayerStrength()));

        firstPlayerDeck = new PlayerDeck(fpo, firstPlayerCards, firPlayerScore, true);
        secondPlayerDeck = new PlayerDeck(spo, secondPlayerCards, secPlayerScore, false);

        firstPlayerDeck.setOppositeDeck(secondPlayerDeck);
        secondPlayerDeck.setOppositeDeck(firstPlayerDeck);
    }

}
