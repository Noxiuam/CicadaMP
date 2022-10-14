package gq.noxiuam.cicadamp.audio;

import gq.noxiuam.cicadamp.CicadaMP;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
@Getter
public class AudioManager {

    @Setter private String currentlySelectedTrack;
    private Clip clip;

    @SneakyThrows
    public void playCurrentTrack() {
        if (isPlaying()) {
            clip.stop();
            clip.close();
        }

        AudioInputStream audioInput = AudioSystem.getAudioInputStream(CicadaMP.class.getClassLoader().getResourceAsStream(this.currentlySelectedTrack + ".wav"));
        clip = AudioSystem.getClip();
        clip.open(audioInput);
        clip.start();
    }

    public void stopPlaying() {
        if (isPlaying()) {
            clip.stop();
            clip.close();
            clip = null;
            this.updateLabel();
        }
    }

    public void updateLabel() {
        boolean playing = this.clip != null;
        CicadaMP.getInstance().getMainWindow().getMusicListComponent().getCurrentTrackLabel().setText(playing ? "Now Playing: " + this.currentlySelectedTrack : "No track playing");
    }

    public boolean isPlaying() {
        return this.clip != null;
    }

}
