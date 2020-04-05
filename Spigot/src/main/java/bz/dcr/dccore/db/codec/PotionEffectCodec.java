package bz.dcr.dccore.db.codec;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionEffectCodec implements Codec<PotionEffect> {

    @Override
    public PotionEffect decode(BsonReader bsonReader, DecoderContext decoderContext) {
        bsonReader.readStartDocument();

        final PotionEffectType potionEffectType = PotionEffectType.getByName(bsonReader.readString("type"));

        // Invalid potion effect type
        if (potionEffectType == null) {
            return null;
        }

        final int duration = bsonReader.readInt32("duration");
        final int amplifier = bsonReader.readInt32("amplifier");
        final boolean ambient = bsonReader.readBoolean("ambient");
        final boolean particles = bsonReader.readBoolean("particles");

        return new PotionEffect(
                potionEffectType,
                duration,
                amplifier,
                ambient,
                particles
        );
    }

    @Override
    public void encode(BsonWriter bsonWriter, PotionEffect potionEffect, EncoderContext encoderContext) {
        bsonWriter.writeStartDocument();

        bsonWriter.writeString("type", potionEffect.getType().getName());
        bsonWriter.writeInt32("duration", potionEffect.getDuration());
        bsonWriter.writeInt32("amplifier", potionEffect.getAmplifier());
        bsonWriter.writeBoolean("ambient", potionEffect.isAmbient());
        bsonWriter.writeBoolean("particles", potionEffect.hasParticles());

        bsonWriter.writeEndDocument();
    }

    @Override
    public Class<PotionEffect> getEncoderClass() {
        return PotionEffect.class;
    }

}
