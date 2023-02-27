package angeATT.Tacocloud.repositories;

import angeATT.Tacocloud.domains.Ingredient;

import java.util.Optional;

public class IngredientRepositoryImpl implements IngredientRepository{
    @Override
    public Iterable<Ingredient> findAll() {
        return null;
    }

    @Override
    public Optional<Ingredient> findById(String id) {

        return Optional.empty();
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return null;
    }
}
