package bz.dcr.dccore.notification;

import bz.dcr.dccore.commons.notification.INotificationManager;
import bz.dcr.dccore.commons.notification.OneTimeNotification;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.List;
import java.util.UUID;

public class NotificationManager implements INotificationManager {

    private Datastore datastore;


    public NotificationManager(Datastore datastore) {
        this.datastore = datastore;
    }


    @Override
    public List<OneTimeNotification> getOneTimeNotifications(UUID targetPlayer) {
        return datastore.createQuery(OneTimeNotification.class)
                .field("targetPlayer").equal(targetPlayer)
                .asList();
    }

    @Override
    public void saveOneTimeNotification(OneTimeNotification notification) {
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
