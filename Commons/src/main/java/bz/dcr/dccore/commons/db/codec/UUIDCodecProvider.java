package bz.dcr.dccore.commons.db.codec;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.UUID;

public class UUIDCodecProvider implements CodecProvider {

    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry codecRegistry) {
        if (clazz == UUID.class) {
            return (Codec<T>) new UUIDCodec();
        }

        return null;
    }

}
