package bz.dcr.dccore.db.codec;

import bz.dcr.dccore.util.ItemStackSerializer;
import org.bson.BsonBinary;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class ItemStackCodec implements Codec<ItemStack> {

    @Override
    public ItemStack decode(BsonReader bsonReader, DecoderContext decoderContext) {
        try {
            return ItemStackSerializer.itemStackFromBase64(bsonReader.readBinaryData().getData());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void encode(BsonWriter bsonWriter, ItemStack itemStack, EncoderContext encoderContext) {
        bsonWriter.writeBinaryData(new BsonBinary(
                ItemStackSerializer.itemStackToBase64(itemStack))
        );
    }

    @Override
    public Class<ItemStack> getEncoderClass() {
        return ItemStack.class;
    }

}
