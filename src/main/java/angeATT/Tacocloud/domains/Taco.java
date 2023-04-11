package angeATT.Tacocloud.domains;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //for data persistence
    private Date createdAt = new Date(); //date de création pour la persistance
    @NotNull
    @Size(min = 5, message = "Entrez un nom d'au moins 5 caractères")
    private String name;
    @NotNull
    @Size(min = 1, message = "Choississez au moins un ingrédient")
    @ManyToMany()
    private List<Ingredient> ingredients;
}
