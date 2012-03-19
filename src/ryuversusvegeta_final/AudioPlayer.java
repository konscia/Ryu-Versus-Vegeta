package ryuversusvegeta_final;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
/*Kyle Zimmerman
 * Jan 8/09
 * 
 * AudioPlayer
 * This class is a wrapper to the Java Sound api which is very low level and hard to work with.
 * The AudioPlayer allows simple playback of a sound files (although not mp3).
 * To use it simply make an instance of it and call play(String fileName).
 */

public class AudioPlayer {

    public AudioPlayer() {
    }

    public static void play(String fileName) {
        AudioPlayer.play(fileName, false);
    }
    
    public static String getSound(String name) {
        String filename = "sounds/" + name + ".wav";
        return filename;
    }

    public static void play(String fileName, boolean loop) {
        //Stores the audio file for accessing
        File file = new File(fileName);

        //Create an Input Stream (can't initialize it here cause java is dumb with exception handleing)
        AudioInputStream stream = null;

        //We'll need to know the format of the file later.
        AudioFormat format;

        //Get a suitible audio stream
        try {
            stream = AudioSystem.getAudioInputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        //find out the format of our stream
        format = stream.getFormat();

        //The clip is what is actually going to play our sound, again java is dumb with exceptions so I can't initialize it here
        Clip clip = null;

        //The DataLine.Info helps get us a clip from the AudioEngine with the right info (Hence the name aparently)
        DataLine.Info info = new DataLine.Info(Clip.class, format);

        try {
            //Get a clip
            clip = (Clip) AudioSystem.getLine(info);
            //Open a stream on the sound card.
            clip.open(stream);
        } catch (Exception e) {
            System.err.println("There was an error obtaining a Clip line!");
        }
        if (loop) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            clip.start();
        }
    }
}