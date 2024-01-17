/*
Programmers: Hamza Khan & Leo Chen
Program Name: SpriteImage
Program Date: 2024-01-16
Program Description: class for playing audio
*/

import java.io.File;
import javax.sound.sampled.*;

public class Music {
	// declare variables
    private Clip clip;
    float vol;
    FloatControl fc;
	
	// constructor
    public Music(String filePath, int loopLength) {
        try {
			// initialize audio
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("Music&SFX/" + filePath)));
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            vol = (float) (Math.log(0.1D) / Math.log(10.0) * 20.0);
            fc.setValue(vol);

            if (loopLength == -1)
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            else
                clip.loop(loopLength);
			stop();
        } catch (Exception e) {
            System.out.println("Something went wrong with the music" + e);
        }
    }
	
	// method for stopping audio
    public void stop() {
        clip.stop();
    }
	
	// method for starting audio
    public void start() {
        clip.start();
    }
}
