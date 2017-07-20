package bz.dcr.dccore.listener;

import bz.dcr.dccore.DcCorePlugin;
import bz.dcr.dccore.commons.identification.CorePlayer;
import bz.dcr.dccore.commons.identification.JoinInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.Optional;

public class LoginListener implements Listener {

    private DcCorePlugin plugin;


    public LoginListener(DcCorePlugin plugin) {
        this.plugin = plugin;
    }


    @EventHandler(priority = EventPriority.HIGH)
    public void onLogin(AsyncPlayerPreLoginEvent event) {
        // Login not allowed
        if(event.getLoginResult() != AsyncPlayerPreLoginEvent.Result.ALLOWED)
            return;

        // Get CorePlayer from PlayerManager
        Optional<CorePlayer> corePlayerOptional = plugin.getPlayerManager().getCorePlayer(event.getUniqueId());

        // Player not found
        if(!corePlayerOptional.isPresent()) {
            CorePlayer corePlayer = new CorePlayer(
                    event.getUniqueId(),
                    event.getName(),
                    new JoinInfo(event.getAddress().getHostAddress())
            );

            // Save new player
            plugin.getPlayerManager().saveCorePlayer(corePlayer);

            return;
        }

        // Update JoinInfo
        corePlayerOptional.get().setLastJoin(new JoinInfo(
                event.getAddress().getHostAddress()
        ));

        // Save updated CorePlayer
        plugin.getPlayerManager().saveCorePlayer(corePlayerOptional.get());
    }

}
