package gq.noxiuam.cicadamp.gui;

import gq.noxiuam.cicadamp.gui.component.MusicListComponent;
import lombok.Getter;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
@Getter
public class MusicPlayerGui {

    private final JFrame frame;

    private final MusicListComponent musicListComponent;

    @SneakyThrows
    public MusicPlayerGui(String title) {
        this.frame = new JFrame(title);

        this.musicListComponent = new MusicListComponent(this.frame);

        this.frame.setIconImage(new ImageIcon(new URL("https://noxiuam.gq/assets/images/cicada.png")).getImage());
        this.frame.setMinimumSize(new Dimension(1024, 576));
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
    }

}
