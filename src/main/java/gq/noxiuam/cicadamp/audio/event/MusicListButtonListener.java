package gq.noxiuam.cicadamp.audio.event;

import gq.noxiuam.cicadamp.CicadaMP;
import gq.noxiuam.cicadamp.audio.AudioManager;
import gq.noxiuam.cicadamp.gui.component.MusicListComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public class MusicListButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        MusicListComponent musicListComponent = CicadaMP.getInstance().getMainWindow().getMusicListComponent();
        AudioManager audioManager = CicadaMP.getInstance().getAudioManager();

        boolean isPlayButton = sourceButton == musicListComponent.getPlayButton();
        boolean isStopButton = sourceButton == musicListComponent.getStopButton();
        boolean isLoopButton = sourceButton == musicListComponent.getLoopButton();

        if (isPlayButton) {
            audioManager.playCurrentTrack();
        } else if (isStopButton) {
            audioManager.stopPlaying();
        } else if (isLoopButton) {
            audioManager.loopPlayer();
        }

        CicadaMP.getInstance().getDiscordRPCController().updateSong();
        audioManager.updateLabel();
    }
}
