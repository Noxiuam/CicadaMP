package gq.noxiuam.cicadamp.gui.component;

import gq.noxiuam.cicadamp.audio.event.MusicListButtonListener;
import gq.noxiuam.cicadamp.audio.event.MusicListListener;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
@Getter
public class MusicListComponent extends Component {

    private final int buttonWidth = 85;
    private final int buttonHeight = 50;

    // panels
    private final JPanel headerPanel = new JPanel();
    private final JPanel centerPanel = new JPanel();
    private final JPanel footerPanel = new JPanel();

    // buttons
    private final JButton playButton = new JButton("Play");
    private final JButton stopButton = new JButton("Stop");
    private final JButton loopButton = new JButton("Loop");

    // header text
    public final JLabel currentTrackLabel = new JLabel("No track playing");

    // music list components
    public JList<String> musicList = new JList<>();
    public DefaultListModel<String> musicListModel = new DefaultListModel<>();

    public MusicListComponent(JFrame frame) {

        // add the songs
        musicListModel.addElement("The Instar Emergence");
        musicListModel.addElement("Interconnectedness");

        // label size
        String currentFontName = this.currentTrackLabel.getFont().getName();
        this.currentTrackLabel.setFont(new Font(currentFontName, Font.BOLD, 21));

        // set list model and size
        this.musicList.setModel(musicListModel);
        this.musicList.setPreferredSize(new Dimension(500, 50));
        this.musicList.addListSelectionListener(new MusicListListener());
        this.musicList.setBackground(new Color(0xFF505050));

        // set button sizes
        this.playButton.setPreferredSize(new Dimension(this.buttonWidth, this.buttonHeight));
        this.stopButton.setPreferredSize(new Dimension(this.buttonWidth, this.buttonHeight));

        this.playButton.addActionListener(new MusicListButtonListener());
        this.stopButton.addActionListener(new MusicListButtonListener());

        // add components to panels
        this.headerPanel.add(this.currentTrackLabel);
        this.centerPanel.add(this.musicList);
        this.footerPanel.add(this.playButton);
        this.footerPanel.add(this.stopButton);

        // add panels to frame
        frame.add(this.headerPanel, BorderLayout.NORTH);
        frame.add(this.centerPanel, BorderLayout.CENTER);
        frame.add(this.footerPanel, BorderLayout.SOUTH);
    }

}
