package ui;

import entityoperation.PlayerOperation;
import util.CardGameConsts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardPanelMouseListener extends MouseAdapter {

    Long PlayerCardId;
    Color origColor;
    PlayerOperation po;

    JLabel playerScoreLabel;

    public CardPanelMouseListener(Long PlayerCardId, PlayerOperation po, JLabel playerScoreLabel) {
        this.PlayerCardId = PlayerCardId;
        this.po = po;
        this.playerScoreLabel = playerScoreLabel;
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("PlayerCardId: " + PlayerCardId);
        po.playCard(PlayerCardId);

        playerScoreLabel.setText(String.valueOf(po.getPlayerStrength()));

        JPanel p = (JPanel) e.getComponent();

        p.setBackground(CardGameConsts.PLAYED_CARD_COLOR);
        origColor = e.getComponent().getBackground();
        p.setOpaque(true);
    }

    public void mouseEntered(MouseEvent e) {
        origColor = e.getComponent().getBackground();

        if (!origColor.equals(CardGameConsts.PLAYED_CARD_COLOR)) {
            e.getComponent().setBackground(CardGameConsts.SELECTED_CARD_COLOR);
        }
    }

    public void mouseExited(MouseEvent e) {
        if (!origColor.equals(CardGameConsts.PLAYED_CARD_COLOR)) {
            e.getComponent().setBackground(origColor);
        }
    }
}
