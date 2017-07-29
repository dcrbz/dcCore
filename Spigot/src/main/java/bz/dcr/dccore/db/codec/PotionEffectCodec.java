package bz.dcr.dccore.db.codec;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionEffectCodec implements Codec<PotionEffect> {

    @Override
    public PotionEffect decode(BsonReader bsonReader, DecoderContext decoderContext) {
        bsonReader.readStartDocument();

        final PotionEffect potionEffect = new PotionEffect(
                PotionEffectType.getByName(bsonReader.readString("type")),
                bsonReader.readInt32("duration"),
                bsonReader.readInt32("amplifier"),
                bsonReader.readBoolean("ambient"),
                bsonReader.readBoolean("particles"),
                readColor(bsonReader, decoderContext, "color")
        );

        bsonReader.readEndDocument();

        return potionEffect;
    }

    @Override
    public void encode(BsonWriter bsonWriter, PotionEffect potionEffect, EncoderContext encoderContext) {
        bsonWriter.writeStartDocument();

        bsonWriter.writeString("type", potionEffect.getType().getName());
        bsonWriter.writeInt32("duration", potionEffect.getDuration());
        bsonWriter.writeInt32("amplifier", potionEffect.getAmplifier());
        bsonWriter.writeBoolean("ambient", potionEffect.isAmbient());
        bsonWriter.writeBoolean("particles", potionEffect.hasParticles());
        writeColor(bsonWriter, potionEffect.getColor(), encoderContext, "color");

        bsonWriter.writeEndDocument();
    }

    @Override
    public Class<PotionEffect> getEncoderClass() {
        return PotionEffect.class;
    }


    private Color readColor(BsonReader bsonReader, DecoderContext decoderContext, String name) {
        bsonReader.readName(name);

        if(bsonReader.getCurrentBsonType() == BsonType.NULL) {
            bsonReader.readNull();
            return null;
        } else {
            return Color.fromRGB(bsonReader.readInt32());
        }
    }

    private void writeColor(BsonWriter bsonWriter, Color color, EncoderContext encoderContext, String name) {
        if(color == null) {
            bsonWriter.writeNull(name);
        } else {
            bsonWriter.writeInt32(name, color.asRGB());
        }
    }

}
