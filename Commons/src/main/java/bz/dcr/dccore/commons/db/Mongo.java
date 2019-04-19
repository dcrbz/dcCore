package bz.dcr.dccore.commons.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class Mongo {

    private MongoClientURI uri;
    private String database;

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;


    public Mongo(MongoClientURI uri, String database) {
        this.uri = uri;
        this.database = database;
    }


    public void connect() {
        mongoClient = new MongoClient(uri);
        mongoDatabase = mongoClient.getDatabase(database);
    }

    public void disconnect() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }


    public MongoClientURI getUri() {
        return uri;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

}
