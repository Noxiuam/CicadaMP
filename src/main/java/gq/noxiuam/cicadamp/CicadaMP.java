package gq.noxiuam.cicadamp;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;
import gq.noxiuam.cicadamp.audio.AudioManager;
import gq.noxiuam.cicadamp.controllers.impl.DiscordRPCController;
import gq.noxiuam.cicadamp.gui.MusicPlayerGui;
import lombok.Getter;

import javax.swing.*;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
@Getter
public class CicadaMP {

    @Getter public static CicadaMP instance;
    public final AudioManager audioManager;
    public final DiscordRPCController discordRPCController;

    // components
    public MusicPlayerGui mainWindow = new MusicPlayerGui("CicadaMP");

    public CicadaMP() {
        instance = this;
        this.audioManager = new AudioManager();
        this.discordRPCController = new DiscordRPCController();
        this.discordRPCController.onLoad();

        LafManager.install(new DarculaTheme());

        JFrame frame = this.mainWindow.getFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CicadaMP::new);
    }

}
