package angeATT.Tacocloud.domains;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@Data
public class Ingredient {
    @Id
    private final String id;
    private final String name;
    @Convert(converter = TypeToStringConverter.class)
    private final Type type;

    public enum Type{
        WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE;
    }

    public static class TypeToStringConverter implements AttributeConverter<Type,String>{

        @Override
        public String convertToDatabaseColumn(Type type) {
            return type.toString();
        }

        @Override
        public Type convertToEntityAttribute(String s) {
            return Type.valueOf(s);
        }
    }

}
