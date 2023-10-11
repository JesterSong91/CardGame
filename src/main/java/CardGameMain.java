import ui.CardGame_UI;

import javax.swing.*;
import java.awt.*;

public class CardGameMain {

    public CardGameMain() {

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JFrame frame = new JFrame("CardGame");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new CardGame_UI().rootPanel);
                frame.setPreferredSize(new Dimension(900, 700));
                frame.pack();
                frame.setVisible(true);

            }
        });

    }
}
