package bz.dcr.dccore.listeners;

import bz.dcr.dccore.DcCorePlugin;
import bz.dcr.dccore.commons.identification.CorePlayer;
import bz.dcr.dccore.commons.identification.JoinInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.Optional;

public class LoginListener implements Listener {

    private DcCorePlugin plugin;


    public LoginListener(DcCorePlugin plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        final Player player = event.getPlayer();

        // Get CorePlayer from PlayerManager
        Optional<CorePlayer> corePlayerOptional = plugin.getPlayerManager().getCorePlayer(player.getUniqueId());

        // Player not found
        if(!corePlayerOptional.isPresent()) {
            CorePlayer corePlayer = new CorePlayer(
                    player.getUniqueId(),
                    player.getName(),
                    new JoinInfo(player.getAddress().getHostString())
            );

            // Save new player
            plugin.getPlayerManager().saveCorePlayer(corePlayer);

            return;
        }

        // Update JoinInfo
        corePlayerOptional.get().setLastJoin(new JoinInfo(
                player.getAddress().getHostString()
        ));

        // Save updated CorePlayer
        plugin.getPlayerManager().saveCorePlayer(corePlayerOptional.get());
    }

}
