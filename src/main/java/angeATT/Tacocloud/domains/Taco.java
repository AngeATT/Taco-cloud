package angeATT.Tacocloud.domains;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class Taco {
    @NotNull
    @Size(min=5,message ="Entrez un nom d'au moins 5 caractères")
    private String name;
    @NotNull
    @Size(min=1,message ="Choississez au moins un ingrédient")
    private List<Ingredient> ingredients;
}
