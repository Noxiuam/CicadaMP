package gq.noxiuam.cicadamp.audio;

import gq.noxiuam.cicadamp.CicadaMP;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
@Getter
public class AudioManager {

    @Setter private String currentlySelectedTrack;

    private Clip clip;

    private boolean looping;

    @SneakyThrows
    public void playCurrentTrack() {
        if (isPlaying()) {
            this.closeClip();
        }

        if (this.currentlySelectedTrack == null)
            return;

        AudioInputStream audioInput = AudioSystem.getAudioInputStream(new BufferedInputStream(CicadaMP.class.getClassLoader().getResourceAsStream(this.currentlySelectedTrack + ".wav")));
        clip = AudioSystem.getClip();
        clip.open(audioInput);
        clip.start();

        if (this.looping)
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        updateLabel();
    }

    public void stopPlaying() {
        if (isPlaying()) {
            this.closeClip();
            this.updateLabel();
        }
    }

    public void loopPlayer() {

        if (!looping) {
            this.looping = true;
            if (clip != null)
                clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            this.looping = false;
            if (clip != null)
                clip.loop(0);
        }

        this.updateLabel();
    }

    public void updateLabel() {
        boolean playing = this.clip != null;
        CicadaMP.getInstance().getMainWindow().getMusicListComponent().getCurrentTrackLabel().setText(
                playing ? "Now" + (this.looping ? " Looping" : " Playing") + ": " + this.currentlySelectedTrack : "No track playing"
        );
    }

    public boolean isPlaying() {
        return this.clip != null;
    }

    public void closeClip() {
        clip.stop();
        clip.close();
        clip = null;
    }

}
