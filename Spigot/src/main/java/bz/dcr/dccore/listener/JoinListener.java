package bz.dcr.dccore.listener;

import bz.dcr.dccore.DcCorePlugin;
import bz.dcr.dccore.commons.notification.OneTimeNotification;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class JoinListener implements Listener {

    private DcCorePlugin plugin;


    public JoinListener(DcCorePlugin plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            // Get notifications for player
            final List<OneTimeNotification> notifications = plugin.getNotificationManager()
                    .getOneTimeNotifications(player.getUniqueId());

            // Send notification message
            notifications.forEach(notification ->
                    plugin.getNotificationManager().sendOneTimeNotification(player, notification));

            // Delete notifications
            plugin.getNotificationManager().deleteOneTimeNotifications(player.getUniqueId());
        });
    }

}
