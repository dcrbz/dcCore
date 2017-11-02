package bz.dcr.dccore.commons.notification;

import java.util.List;
import java.util.UUID;

public interface INotificationManager {

    List<OneTimeNotification> getOneTimeNotifications(UUID targetPlayer);

    void saveOneTimeNotification(OneTimeNotification notification);
    void deleteOneTimeNotifications(UUID targetPlayer);

}
