package CalorieCalculator.init;


import CalorieCalculator.service.IngredientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataInit implements CommandLineRunner {
    private final IngredientService ingredientService;

    public DataInit(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    // private final UserService userService;


    @Override
    public void run(String... args) throws Exception {
       this.ingredientService.addIngredientsAtFirst();
       // this.userService.createAdmin();
    }
}
