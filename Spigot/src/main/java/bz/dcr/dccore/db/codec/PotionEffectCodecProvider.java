package bz.dcr.dccore.db.codec;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bukkit.potion.PotionEffect;

public class PotionEffectCodecProvider implements CodecProvider {

    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if(clazz == PotionEffect.class) {
            return (Codec<T>) new PotionEffectCodec();
        }

        return null;
    }

}
