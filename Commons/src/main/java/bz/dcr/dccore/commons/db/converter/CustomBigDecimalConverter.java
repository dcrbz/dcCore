package bz.dcr.dccore.commons.db.converter;

import org.mongodb.morphia.converters.BigDecimalConverter;
import org.mongodb.morphia.mapping.MappedField;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CustomBigDecimalConverter extends BigDecimalConverter {

    public CustomBigDecimalConverter() {
        super();
    }


    @Override
    public Object encode(final Object value, final MappedField optionalExtraInfo) {
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).setScale(10, RoundingMode.CEILING);
        }
        return super.encode(value, optionalExtraInfo);
    }

}
