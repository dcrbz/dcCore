package bz.dcr.dccore.db.codec;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bukkit.inventory.ItemStack;

public class ItemStackCodecProvider implements CodecProvider {

    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry codecRegistry) {
        if(clazz == ItemStack.class) {
            return (Codec<T>) new ItemStackCodec();
        }

        return null;
    }

}
