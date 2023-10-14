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

    public Player firstPlayer;
    public Player secondPlayer;

    private ArrayList<JPanel> firstPlayerCards;
    private ArrayList<JPanel> secondPlayerCards;

    public CardGame_UI() {
        co = new CardOperation();
        fpo = new PlayerOperation("John");
        spo = new PlayerOperation("Mixy");
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
        firstPlayer = fpo.getPlayer("John");
        firstPlayerName.setText(firstPlayer.getName());

        secondPlayer = spo.getPlayer("Mixy");
        secondPlayerName.setText(secondPlayer.getName());

        createPlayerDeck(fpo, firstPlayerCards);
        createPlayerDeck(spo, secondPlayerCards);
    }

    public void createPlayerDeck(PlayerOperation po, ArrayList<JPanel> playerCards) {
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
                }
            }

            i++;
        }
    }

}
