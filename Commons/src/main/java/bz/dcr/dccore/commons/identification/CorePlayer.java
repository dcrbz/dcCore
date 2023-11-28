package bz.dcr.dccore.commons.identification;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.IndexOptions;
import dev.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

import java.util.UUID;

@Entity
public class CorePlayer {

    @Id
    private ObjectId id;

    @Indexed(options = @IndexOptions(unique = true))
    private UUID uuid;
    private String name;
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
