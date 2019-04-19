package bz.dcr.dccore.listeners;

import bz.dcr.dccore.DcCorePlugin;
import bz.dcr.dccore.commons.identification.CorePlayer;
import bz.dcr.dccore.commons.identification.JoinInfo;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Optional;

public class LoginListener implements Listener {

    private DcCorePlugin plugin;


    public LoginListener(DcCorePlugin plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onLogin(LoginEvent event) {
        PendingConnection connection = event.getConnection();

        // Get CorePlayer from PlayerManager
        Optional<CorePlayer> corePlayer = plugin.getPlayerManager().getCorePlayer(connection.getUniqueId());

        // Player not found
        if (!corePlayer.isPresent()) {
            CorePlayer player = new CorePlayer(
                    connection.getUniqueId(),
                    connection.getName(),
                    new JoinInfo(connection.getAddress().getHostString())
            );

            // Save new player
            plugin.getPlayerManager().saveCorePlayer(player);

            return;
        }

        // Update name
        corePlayer.get().setName(connection.getName());

        // Update JoinInfo
        corePlayer.get().setLastJoin(new JoinInfo(
                connection.getAddress().getHostString()
        ));

        // Save updated CorePlayer
        plugin.getPlayerManager().saveCorePlayer(corePlayer.get());
    }

}
