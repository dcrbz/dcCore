package bz.dcr.dccore.player;

import bz.dcr.dccore.commons.identification.CorePlayer;
import bz.dcr.dccore.commons.player.AbstractPlayerManager;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

import java.util.Optional;
import java.util.UUID;

public class PlayerManager extends AbstractPlayerManager {

    private Datastore datastore;


    public PlayerManager(Datastore datastore) {
        this.datastore = datastore;
    }


    @Override
    public Optional<CorePlayer> getCorePlayer(UUID uuid) {
        CorePlayer corePlayer = datastore.createQuery(CorePlayer.class)
                .field("uuid").equal(uuid)
                .get();

        return Optional.ofNullable(corePlayer);
    }

    @Override
    public Key<CorePlayer> saveCorePlayer(CorePlayer corePlayer) {
        return datastore.save(corePlayer);
    }

}
