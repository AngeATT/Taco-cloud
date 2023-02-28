package angeATT.Tacocloud.domains;

import lombok.Data;
import lombok.NonNull;

@Data
public class Ingredient {
    private final String id2;
    private final String id;
    private final String name;
    private final Type type;

    public enum Type{
        WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE;
    }
}
