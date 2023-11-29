package bz.dcr.dccore.commons.db;

import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.mapping.MapperOptions;

import java.io.Closeable;

public class MongoDB implements Closeable {

    private final MongoClient client;
    private final Datastore datastore;


    public MongoDB(MongoClientURI uri, ClassLoader classLoader) {
        client = MongoClients.create(uri.getURI());
        var mapperOptions = MapperOptions
                .builder(MapperOptions.DEFAULT)
                .classLoader(classLoader)
                .build();
        datastore = Morphia.createDatastore(client, uri.getDatabase(), mapperOptions);
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
