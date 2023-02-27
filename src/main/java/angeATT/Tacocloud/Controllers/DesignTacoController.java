package angeATT.Tacocloud.Controllers;

import angeATT.Tacocloud.domains.Ingredient;
import angeATT.Tacocloud.domains.Ingredient.Type;
import angeATT.Tacocloud.domains.Taco;
import angeATT.Tacocloud.domains.TacoOrder;
import angeATT.Tacocloud.repositories.IngredientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    private final IngredientRepository ingredientRepo;
    @Autowired
    public DesignTacoController(
            IngredientRepository ingredientRepository) {
        this.ingredientRepo = ingredientRepository;
    }
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType((List<Ingredient>) ingredients, type));
        }
    }

    Iterable<Ingredient> filterByType( List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder tacoOrder() {
        return new TacoOrder();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }
    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder){
        if (errors.hasErrors()){
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("log tacos :{}",taco);
        return "redirect:/orders/current";
    }
}
