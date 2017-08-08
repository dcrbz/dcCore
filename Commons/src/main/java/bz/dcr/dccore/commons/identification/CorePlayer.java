package bz.dcr.dccore.commons.identification;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

import java.util.UUID;

@Entity
public class CorePlayer {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private UUID uuid;
    private String name;
    @Embedded
    private JoinInfo lastJoin;


    public CorePlayer() {
    }

    public CorePlayer(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public CorePlayer(UUID uuid, String name, JoinInfo lastJoin) {
        this.uuid = uuid;
        this.name = name;
        this.lastJoin = lastJoin;
    }


    public ObjectId getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JoinInfo getLastJoin() {
        return lastJoin;
    }

    public void setLastJoin(JoinInfo lastJoin) {
        this.lastJoin = lastJoin;
    }

}
