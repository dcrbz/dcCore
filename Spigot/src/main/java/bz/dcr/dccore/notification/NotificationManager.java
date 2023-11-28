package bz.dcr.dccore.notification;

import bz.dcr.dccore.commons.notification.INotificationManager;
import bz.dcr.dccore.commons.notification.OneTimeNotification;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.UUID;

public class NotificationManager implements INotificationManager {

    private final Plugin plugin;
    private final Datastore datastore;


    public NotificationManager(Plugin plugin, Datastore datastore) {
        this.plugin = plugin;
        this.datastore = datastore;
    }


    @Override
    public OneTimeNotification createOneTimeNotification(UUID targetPlayer, String message) {
        // Create notification
        final OneTimeNotification notification = new OneTimeNotification(
                targetPlayer, message, false
        );

        // Get player
        final Player player = Bukkit.getPlayer(notification.getTargetPlayer());

        // Player is currently online
        if (player != null) {
            sendOneTimeNotification(player, notification);
            return notification;
        }

        // Save notification
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () ->
                saveOneTimeNotification(notification));

        return notification;
    }

    @Override
    public void createOneTimeNotification(Iterable<UUID> targets, String message) {
        targets.forEach(target -> createOneTimeNotification(target, message));
    }

    public void sendOneTimeNotification(Player player, OneTimeNotification notification) {
        player.sendMessage(notification.getMessage());
    }

    @Override
    public List<OneTimeNotification> getOneTimeNotifications(UUID targetPlayer) {
        return datastore.createQuery(OneTimeNotification.class)
                .field("targetPlayer").equal(targetPlayer)
                .iterator()
                .toList();
    }

    private void saveOneTimeNotification(OneTimeNotification notification) {
        datastore.save(notification);
    }

    @Override
    public void deleteOneTimeNotifications(UUID targetPlayer) {
        final Query<OneTimeNotification> query = datastore
                .createQuery(OneTimeNotification.class);

        query.field("targetPlayer").equal(targetPlayer);

        datastore.delete(query);
    }

}
