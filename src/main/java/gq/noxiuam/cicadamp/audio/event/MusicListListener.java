package gq.noxiuam.cicadamp.audio.event;

import gq.noxiuam.cicadamp.CicadaMP;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public class MusicListListener implements ListSelectionListener {

    // update the currently selected song, this is so the audio manager knows what song to play.
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList sourceModel = (JList) e.getSource();

        if (sourceModel == CicadaMP.getInstance().getMainWindow().getMusicListComponent().getMusicList()) {
            String songName = sourceModel.getSelectedValue().toString();
            CicadaMP.getInstance().audioManager.setCurrentlySelectedTrack(songName);
        }

    }

}
