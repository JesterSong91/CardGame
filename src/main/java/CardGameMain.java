import ui.CardGame_UI;
import util.MyAudioEffect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static util.CardGameConsts.PATH_TO_SOUNDS;

public class CardGameMain {

    public CardGameMain() {

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JFrame frame = new JFrame("CardGame");
                CardGame_UI cg = new CardGame_UI();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(cg.rootPanel);
                frame.setPreferredSize(new Dimension(900, 700));
                frame.pack();
                frame.setVisible(true);

                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        cg.pco.resetPlayerCards();
                    }
                });

                new MyAudioEffect(PATH_TO_SOUNDS + "CardGameMain2.wav", true).play();
            }
        });

    }
}
