import ui.CardGame_UI;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import static javax.sound.sampled.Clip.LOOP_CONTINUOUSLY;
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

                AudioInputStream stream        = null;
                try {
                    stream = AudioSystem.getAudioInputStream(new File(PATH_TO_SOUNDS + "CardGameMain.wav"));
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
                clip.loop(LOOP_CONTINUOUSLY);
                clip.start();

            }
        });

    }
}
