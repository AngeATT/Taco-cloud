package angeATT.Tacocloud.service;

import angeATT.Tacocloud.domains.Ingredient;

public interface IngredientService {
     Iterable<Ingredient> findAll();
     Ingredient addIngredient(Ingredient ingredient);
}
