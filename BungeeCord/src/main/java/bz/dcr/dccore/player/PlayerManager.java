package bz.dcr.dccore.player;

import bz.dcr.dccore.commons.identification.CorePlayer;
import bz.dcr.dccore.commons.player.AbstractPlayerManager;
import dev.morphia.Datastore;

import java.util.Optional;
import java.util.UUID;

public class PlayerManager extends AbstractPlayerManager {

    private final Datastore datastore;


    public PlayerManager(Datastore datastore) {
        this.datastore = datastore;
    }


    @Override
    public Optional<CorePlayer> getCorePlayer(UUID uuid) {
        CorePlayer corePlayer = datastore.createQuery(CorePlayer.class)
                .field("uuid").equal(uuid)
                .first();

        return Optional.ofNullable(corePlayer);
    }

    @Override
    public CorePlayer saveCorePlayer(CorePlayer corePlayer) {
        return datastore.save(corePlayer);
    }

}
