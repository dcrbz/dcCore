package bz.dcr.dccore.db.codec;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionEffectCodec implements Codec<PotionEffect> {

    private DocumentCodec documentCodec = new DocumentCodec();

    @Override
    public PotionEffect decode(BsonReader reader, DecoderContext decoderContext) {
        final Document doc = documentCodec.decode(reader, decoderContext);

        final PotionEffectType potionEffectType = PotionEffectType.getByName(doc.getString("type"));

        // Invalid potion effect type
        if (potionEffectType == null) {
            return null;
        }

        final int duration = doc.getInteger("duration");
        final int amplifier = doc.getInteger("amplifier");
        final boolean ambient = doc.getBoolean("ambient");
        final boolean particles = doc.getBoolean("particles");

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
