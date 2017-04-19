package bz.dcr.dccore.identification;

import bz.dcr.dccore.commons.identification.AbstractIdentificationProvider;
import bz.dcr.dccore.commons.identification.CorePlayer;
import org.mongodb.morphia.Datastore;

import java.util.UUID;

public class IdentificationProvider extends AbstractIdentificationProvider {

    private Datastore datastore;


    public IdentificationProvider(Datastore datastore) {
        this.datastore = datastore;
    }


    @Override
    public UUID getUUID(String name) {
        CorePlayer player = getCorePlayerFromDatabase(name);

        // Player not found in database
        if(player == null) {
            return getUUIDFromMojang(name);
        }

        return player.getUuid();
    }

    @Override
    public String getName(UUID uuid) {
        CorePlayer player = getCorePlayerFromDatabase(uuid);

        // Player not found in database
        if(player == null) {
            return getNameFromMojang(uuid);
        }

        return player.getName();
    }


    private CorePlayer getCorePlayerFromDatabase(UUID uuid) {
        return datastore.createQuery(CorePlayer.class)
                .field("uuid").equal(uuid)
                .get();
    }

    private CorePlayer getCorePlayerFromDatabase(String name) {
        return datastore.createQuery(CorePlayer.class)
                .field("name").equal(name)
                .get();
    }

}
