package bz.dcr.dccore.commons.db;

import bz.dcr.dccore.commons.db.converter.CustomBigDecimalConverter;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.mapping.DefaultCreator;

import java.io.Closeable;

public class MongoDB implements Closeable {

    private Morphia morphia;
    private MongoClient client;
    private Datastore datastore;


    public MongoDB(MongoClientURI uri, ClassLoader classLoader) {
        morphia = new Morphia();
        morphia.getMapper().getConverters().addConverter(CustomBigDecimalConverter.class);
        morphia.getMapper().getOptions().setObjectFactory(new DefaultCreator() {
            @Override
            protected ClassLoader getClassLoaderForClass() {
                return classLoader;
            }
        });
        client = new MongoClient(uri);
        datastore = morphia.createDatastore(client, uri.getDatabase());
        datastore.ensureIndexes();
    }


    @Override
    public void close() {
        if(client != null)
            client.close();
    }


    public Morphia getMorphia() {
        return morphia;
    }

    public MongoClient getClient() {
        return client;
    }

    public Datastore getDatastore() {
        return datastore;
    }

}
