package bz.dcr.dccore.commons.db;

import bz.dcr.dccore.commons.db.codec.UUIDCodecProvider;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.mapping.MapperOptions;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecProvider;

import java.io.Closeable;

public class MongoDB implements Closeable {

    private final MongoClient client;
    private final Datastore datastore;


    public MongoDB(MongoClientURI uri, ClassLoader classLoader, CodecProvider... codecProviders) {
        client = MongoClients.create(uri.getURI());
        var mapperOptions = MapperOptions
                .builder()
                .codecProvider(new UUIDCodecProvider())
                .classLoader(classLoader)
                .uuidRepresentation(UuidRepresentation.JAVA_LEGACY);

        for (CodecProvider codecProvider : codecProviders) {
            mapperOptions.codecProvider(codecProvider);
        }

        datastore = Morphia.createDatastore(client, uri.getDatabase(), mapperOptions.build());
    }


    @Override
    public void close() {
        if (client != null)
            client.close();
    }

    public MongoClient getClient() {
        return client;
    }

    public Datastore getDatastore() {
        return datastore;
    }

}
