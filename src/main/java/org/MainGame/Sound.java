package org.MainGame;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/**Sound class handles all sound related tasks and holds their information inside of it. The class has methods to play music
 * setMusic to a Sound Object and stop the clip and loop it and flush all the clips.
 * @author andrewkim
 * @since November 26th, 2023
 * @version 1.0
 */
public class Sound {
    Clip clip;
    URL[] soundURL = new URL[8];

    /**
     * Sound Constructor will load all the relevant audio files into its array to be used by other methods.
     */
    public Sound(){
        soundURL[0]=getClass().getResource("/music/Ghost Alley.wav");
        soundURL[1]=getClass().getResource("/music/Music1.wav");
        soundURL[2]=getClass().getResource("/music/To Suffer a Loss (Game Over).wav");
        soundURL[3]=getClass().getResource("/music/Victorious.wav");
        soundURL[4]=getClass().getResource("/music/Coin 1.wav");
        soundURL[5]=getClass().getResource("/music/Power Up 1.wav");
        soundURL[6]=getClass().getResource("/music/Hit 1.wav");
        soundURL[7]=getClass().getResource("/music/Success 1.wav");
    }

    /**
     * setFile method will take a soundURL from the array and open it as a clip.
     * @param i is the index for choosing which sound file is played
     */
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip=AudioSystem.getClip();
            clip.open(ais);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method will start the clip
     */
    public void play() {
        if (clip != null) {
            if (!clip.isActive()) {
                clip.setFramePosition(0);
            }
            clip.start();
        }
    }

    /**
     * method will loop the clip continuously
     */
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * method will stop the clip.
     */
    public void stop(){
        if (clip!=null) {
            clip.stop();
        }
    }

}
