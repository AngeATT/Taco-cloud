package angeATT.Tacocloud.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@Data
public class Ingredient {
    private final String id2;
    @Id
    private final String id;
    private final String name;
    private final Type type;

    public enum Type{
        WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE;
    }
}
