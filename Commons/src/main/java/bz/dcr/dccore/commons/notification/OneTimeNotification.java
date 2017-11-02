package bz.dcr.dccore.commons.notification;

import org.mongodb.morphia.annotations.Entity;

import java.util.UUID;

@Entity
public class OneTimeNotification extends Notifaction {

    private boolean shown;


    public OneTimeNotification() {
        super(null, "");
    }

    public OneTimeNotification(UUID targetPlayer, String message, boolean shown) {
        super(targetPlayer, message);

        this.shown = shown;
    }


    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

}
