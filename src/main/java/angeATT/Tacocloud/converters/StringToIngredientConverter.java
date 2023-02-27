package angeATT.Tacocloud.converters;

import angeATT.Tacocloud.domains.Ingredient;
import angeATT.Tacocloud.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToIngredientConverter implements Converter<String, Ingredient> {
    IngredientRepository ingredientRepo;

    @Autowired
    StringToIngredientConverter(IngredientRepository ingredientRepository){
        this.ingredientRepo = ingredientRepository;
    }
    @Override
    public Ingredient convert(String source) {
        return ingredientRepo.findById(source).orElse(null);
    }
}
