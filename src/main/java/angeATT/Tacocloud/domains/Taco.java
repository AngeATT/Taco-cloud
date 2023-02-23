package angeATT.Tacocloud.domains;


import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class Taco {
    private String name;
    private List<Ingredient> ingredients;
}
