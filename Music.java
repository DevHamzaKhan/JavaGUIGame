
import java.io.File;
import javax.sound.sampled.*;

public class Music {
    private Clip clip;
    float vol;
    FloatControl fc;
    public Music(String filePath, int loopLength) {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filePath)));
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
    public void stop() {
        clip.stop();
    }
    public void start() {
        clip.start();
    }
}