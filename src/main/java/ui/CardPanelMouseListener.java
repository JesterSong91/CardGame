package ui;

import entityoperation.PlayerOperation;
import util.CardGameConsts;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import static javafx.application.Platform.exit;
import static javax.sound.sampled.Clip.LOOP_CONTINUOUSLY;
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

        AudioInputStream stream = null;
        try {
            stream = AudioSystem.getAudioInputStream(new File(PATH_TO_SOUNDS+ "PlayingCards.wav"));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AudioFormat format = stream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat());
        Clip clip = null;
        try {
            clip = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            clip.open(stream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clip.start();


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
