package util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import static javax.sound.sampled.Clip.LOOP_CONTINUOUSLY;

public class MyAudioEffect {

    private String fileName;
    private boolean isRepeated;
    private Clip clip;

    public MyAudioEffect(String fileName, boolean isRepeated) {
        this.fileName = fileName;
        this.isRepeated = isRepeated;

        AudioInputStream stream = null;
        try {
            stream = AudioSystem.getAudioInputStream(new File(fileName));
        } catch (UnsupportedAudioFileException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        AudioFormat format = stream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat());
        clip = null;
        try {
            clip = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException ex) {
            throw new RuntimeException(ex);
        }
        try {
            clip.open(stream);
        } catch (LineUnavailableException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void play() {
        if (isRepeated) {
            clip.loop(LOOP_CONTINUOUSLY);
        }

        clip.start();
    }
    }
