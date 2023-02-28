package angeATT.Tacocloud.repositories;

import java.util.Optional;
import angeATT.Tacocloud.domains.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface IngredientRepository extends CrudRepository<Ingredient,String> {


}