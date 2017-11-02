package bz.dcr.dccore.commons.notification;

import java.util.UUID;

public abstract class Notifaction {

    private UUID targetPlayer;
    private String message;


    public Notifaction(UUID targetPlayer, String message) {
        this.targetPlayer = targetPlayer;
        this.message = message;
    }


    public UUID getTargetPlayer() {
        return targetPlayer;
    }

    public void setTargetPlayer(UUID targetPlayer) {
        this.targetPlayer = targetPlayer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
