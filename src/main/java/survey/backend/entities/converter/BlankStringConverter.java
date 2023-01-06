package survey.backend.entities.converter;

import javax.persistence.AttributeConverter;
import java.util.Objects;

public class BlankStringConverter
        implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String attribute) {
        return  (Objects.isNull(attribute) || attribute.isBlank())
                ? null : attribute;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
}
