package angeATT.Tacocloud;

import angeATT.Tacocloud.domains.Ingredient;
import angeATT.Tacocloud.domains.Ingredient.Type;
import angeATT.Tacocloud.repositories.IngredientRepository;
import angeATT.Tacocloud.sec.Utilisateur;
import angeATT.Tacocloud.sec.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TacoCloudProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo, UtilisateurRepository utilisateurRepository) {
		return args -> {
			repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
			repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
			repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
			repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
			repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
			repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
			repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
			repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
			repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
			repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));


		};
	}

}
