package angeATT.Tacocloud.domains;

import lombok.Data;
import lombok.NonNull;

@Data
public class Ingredient {
    @NonNull
    private final String id;
    @NonNull
    private final String name;
    @NonNull
    private final Type type;

    public enum Type{
        WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE;
    }
}
