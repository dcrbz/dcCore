package bz.dcr.dccore.commons.db.codec;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.util.UUID;

public class UUIDCodec implements Codec<UUID> {

    @Override
    public UUID decode(BsonReader bsonReader, DecoderContext decoderContext) {
        return UUID.fromString(bsonReader.readString());
    }

    @Override
    public void encode(BsonWriter bsonWriter, UUID uuid, EncoderContext encoderContext) {
        bsonWriter.writeString(uuid.toString());
    }

    @Override
    public Class<UUID> getEncoderClass() {
        return UUID.class;
    }

}
