package ui;

import entityoperation.PlayerOperation;
import util.MyAudioEffect;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static util.CardGameConsts.PATH_TO_IMAGES;
import static util.CardGameConsts.PATH_TO_SOUNDS;

public class CardPanelMouseListener extends MouseAdapter {

    private Long PlayerCardId;
    private PlayerOperation po;

    private JLabel playerScoreLabel;

    private PlayerDeck pd;

    private JLabel cardImageLabel;

    private JLabel origImageLabel;
    private int cardCond = 0;

    private boolean isPlayed = false;

    private String cardName;

    public CardPanelMouseListener(Long PlayerCardId, PlayerOperation po, JLabel playerScoreLabel, PlayerDeck pd, String cardName) {
        this.PlayerCardId = PlayerCardId;
        this.po = po;
        this.playerScoreLabel = playerScoreLabel;
        this.pd = pd;
        this.cardName = cardName;
    }

    public JLabel getCardImageLabel() {
        return cardImageLabel;
    }

    public void setCardImageLabel(JLabel cardImageLabel) {
        this.cardImageLabel = cardImageLabel;
        cardCond = 1;

        origImageLabel = cardImageLabel;
    }

    public void mouseClicked(MouseEvent ex) {
        if (!pd.isHaveToMove()) {
            return;
        }

        new MyAudioEffect(PATH_TO_SOUNDS + "PlayingCards.wav", false).play();

        System.out.println("PlayerCardId: " + PlayerCardId);
        po.playCard(PlayerCardId);

        playerScoreLabel.setText(String.valueOf(po.getPlayerStrength()));

        JPanel p = (JPanel) ex.getComponent();

        if (cardCond == 1) {
            BufferedImage wPic = null;
            try {
                wPic = ImageIO.read(new File(PATH_TO_IMAGES + cardName + "Played.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            cardImageLabel.setIcon(new ImageIcon(wPic));
        }

        if (po.getCardsQuantity() == 0) {
            System.out.println("Gave over!");
            return;
        }

        isPlayed = true;

        pd.oppositeDeckMove();
    }

    public void mouseEntered(MouseEvent e) {
        if (!pd.isHaveToMove() || isPlayed) {
            return;
        }

        new MyAudioEffect(PATH_TO_SOUNDS + "SelectCardCutted.wav", false).play();

        if (cardCond == 1) {
            BufferedImage wPic = null;
            try {
                wPic = ImageIO.read(new File(PATH_TO_IMAGES + cardName + "Selected.png"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            cardImageLabel.setIcon(new ImageIcon(wPic));
        }
    }

    public void mouseExited(MouseEvent e) {
        if (!isPlayed) {
            if (cardCond == 1) {
                BufferedImage wPic = null;
                try {
                    wPic = ImageIO.read(new File(PATH_TO_IMAGES + cardName + ".png"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                cardImageLabel.setIcon(new ImageIcon(wPic));
            }

            return;
        }

        if (!pd.isHaveToMove()) {
            return;
        }
    }

    public void setCardCondImage() {

    }
}
