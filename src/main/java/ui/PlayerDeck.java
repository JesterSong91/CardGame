package ui;

import entity.Card;
import entity.PlayerCards;
import entityoperation.CardOperation;
import entityoperation.PlayerOperation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static util.CardGameConsts.PATH_TO_IMAGES;

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

        boolean isImageCard;

        for (PlayerCards currPC : po.getPlayerCards()
        ) {
            isImageCard = false;
            for (Card currCard : cards
            ) {
                JLabel wIcon = null;

                if (currPC.getCard_id() == currCard.getId()) {
                    JLabel currUnitName = new JLabel(currCard.getCard_name());
                    JLabel currStrength = new JLabel(String.valueOf(currCard.getStrength()));
                    JLabel currPlayerCardId = new JLabel(String.valueOf(currPC.getId()));

                    BufferedImage wPic = null;
                    String imagePath = null;

                    if (currCard.getCard_name().equals("Clocker")) {
                        imagePath = PATH_TO_IMAGES + "Clocker.png";
                        isImageCard = true;
                    }
                    else if (currCard.getCard_name().equals("Clock")) {
                        imagePath = PATH_TO_IMAGES + "Clock.png";
                        isImageCard = true;
                    }
                    else if (currCard.getCard_name().equals("Pain")) {
                        imagePath = PATH_TO_IMAGES + "Pain.png";
                        isImageCard = true;
                    }

                    if (isImageCard) {
                        try {
                            wPic = ImageIO.read(new File(imagePath));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        wIcon = new JLabel(new ImageIcon(wPic));

                        playerCards.get(i).add(wIcon);
                    }
                    else {
                        playerCards.get(i).add(currUnitName);
                        playerCards.get(i).add(currStrength);
                        playerCards.get(i).add(currPlayerCardId);
                    }

                    CardPanelMouseListener tempML = new CardPanelMouseListener(currPC.getId(), po, playerScoreLabel, this, currCard.getCard_name());
                    if (isImageCard) {
                        tempML.setCardImageLabel(wIcon);
                    }
                    playerCards.get(i).addMouseListener(tempML);
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
