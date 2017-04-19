package bz.dcr.dccore.commons.player;

import bz.dcr.dccore.commons.identification.CorePlayer;
import org.mongodb.morphia.Key;

import java.util.Optional;
import java.util.UUID;

public abstract class AbstractPlayerManager {

    public abstract Optional<CorePlayer> getCorePlayer(UUID uuid);

    public abstract Key<CorePlayer> saveCorePlayer(CorePlayer corePlayer);

}
