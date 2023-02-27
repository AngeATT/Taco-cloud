package angeATT.Tacocloud.repositories;

import java.util.Optional;
import angeATT.Tacocloud.domains.Ingredient;
public interface IngredientRepository {
    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);

}