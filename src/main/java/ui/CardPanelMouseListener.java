package ui;

import entityoperation.PlayerOperation;
import util.CardGameConsts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static javafx.application.Platform.exit;

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

    public void mouseClicked(MouseEvent e) {
        if (!pd.isHaveToMove()) {
            return;
        }

        System.out.println("PlayerCardId: " + PlayerCardId);
        po.playCard(PlayerCardId);

        playerScoreLabel.setText(String.valueOf(po.getPlayerStrength()));

        JPanel p = (JPanel) e.getComponent();

        p.setBackground(CardGameConsts.PLAYED_CARD_COLOR);
        origColor = e.getComponent().getBackground();
        p.setOpaque(true);

        if (po.getCardsQuantity() == 0) {
            System.out.println("Gave over!");
            return;
//            exit();
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
