package bz.dcr.dccore.commons.notification;

import java.util.List;
import java.util.UUID;

public interface INotificationManager {

    OneTimeNotification createOneTimeNotification(UUID targetPlayer, String message);

    void createOneTimeNotification(Iterable<UUID> targets, String message);

    List<OneTimeNotification> getOneTimeNotifications(UUID targetPlayer);

    void deleteOneTimeNotifications(UUID targetPlayer);

}
