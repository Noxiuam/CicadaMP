package gq.noxiuam.cicadamp.controllers;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.RichPresence;
import gq.noxiuam.cicadamp.CicadaMP;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Noxiuam
 * https://noxiuam.gq
 */
public class DiscordRPCController extends Controller {
    @Getter @Setter
    private IPCClient rpcClient;

    private void connectToDiscord() {
        try {
            IPCClient client = new IPCClient(1030530362264260679L);

            client.setListener(new IPCListener() {
                @Override
                public void onReady(IPCClient client) {
                    setRpcClient(client);
                    updateSong();
                }
            });

            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSong() {
        boolean playing = CicadaMP.getInstance().getAudioManager().isPlaying();
        String song = CicadaMP.getInstance().getAudioManager().getCurrentlySelectedTrack();
        String state = playing ? "Current track:" : "Idling";

        if (song == null) {
            state = "Just started the application";
            song = "Nothing playing";
        }

        if (!playing) song = "Nothing playing";

        RichPresence.Builder rpc = new RichPresence.Builder();
        rpc.setDetails(state).setState(song).setLargeImage("everywhere");
        this.getRpcClient().sendRichPresence(rpc.build());
    }

    @Override
    public void onLoad() {
        connectToDiscord();
    }
}
