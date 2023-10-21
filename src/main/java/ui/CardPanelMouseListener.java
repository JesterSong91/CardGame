package ui;

import entityoperation.PlayerOperation;
import util.CardGameConsts;
import util.MyAudioEffect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static util.CardGameConsts.PATH_TO_SOUNDS;

public class CardPanelMouseListener extends MouseAdapter {

    private Long PlayerCardId;
    private Color origColor;
    private PlayerOperation po;

    private JLabel playerScoreLabel;

    private PlayerDeck pd;

    public CardPanelMouseListener(Long PlayerCardId, PlayerOperation po, JLabel playerScoreLabel, PlayerDeck pd) {
        this.PlayerCardId = PlayerCardId;
        this.po = po;
        this.playerScoreLabel = playerScoreLabel;
        this.pd = pd;
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

        p.setBackground(CardGameConsts.PLAYED_CARD_COLOR);
        origColor = ex.getComponent().getBackground();
        p.setOpaque(true);

        if (po.getCardsQuantity() == 0) {
            System.out.println("Gave over!");
            return;
        }

        pd.oppositeDeckMove();
    }

    public void mouseEntered(MouseEvent e) {
        if (!pd.isHaveToMove()) {
            return;
        }

        origColor = e.getComponent().getBackground();

        if (!origColor.equals(CardGameConsts.PLAYED_CARD_COLOR)) {
            e.getComponent().setBackground(CardGameConsts.SELECTED_CARD_COLOR);
        }

        new MyAudioEffect(PATH_TO_SOUNDS + "SelectCardCutted.wav", false).play();
    }

    public void mouseExited(MouseEvent e) {
        if (!pd.isHaveToMove()) {
            return;
        }

        if (!origColor.equals(CardGameConsts.PLAYED_CARD_COLOR)) {
            e.getComponent().setBackground(origColor);
        }
    }
}
