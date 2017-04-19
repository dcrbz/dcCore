package bz.dcr.dccore.commons.identification;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public abstract class AbstractIdentificationProvider {

    private final ExecutorService executor = Executors.newCachedThreadPool();


    public abstract UUID getUUID(String name);

    public void getUUID(String name, Consumer<UUID> consumer) {
        executor.execute(() -> consumer.accept(getUUID(name)));
    }


    public UUID getUUIDFromMojang(String name) {
        return UUIDFetcher.getUUID(name);
    }

    public void getUUIDFromMojang(String name, Consumer<UUID> consumer) {
        UUIDFetcher.getUUID(name, consumer);
    }


    public abstract String getName(UUID uuid);

    public void getName(UUID uuid, Consumer<String> consumer) {
        executor.execute(() -> consumer.accept(getName(uuid)));
    }


    public String getNameFromMojang(UUID uuid) {
        return UUIDFetcher.getName(uuid);
    }

    public void getNameFromMojang(UUID uuid, Consumer<String> consumer) {
        UUIDFetcher.getName(uuid, consumer);
    }

}
