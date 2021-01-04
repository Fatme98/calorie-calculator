package CalorieCalculator.service;

import CalorieCalculator.model.entity.Ingredient;
import CalorieCalculator.model.service.IngredientServiceModel;
import CalorieCalculator.model.view.IngredientViewModel;

import java.util.List;

public interface IngredientService {
    void addIngredient(IngredientServiceModel ingredientServiceModel, String mealId);
    List<IngredientViewModel>findAllIngredientsByMealId(String id);
    double totalCaloriesForMealById(String mealId);
    IngredientViewModel searchAndAddToMeal(String name,String mealId,double grams);
    void addIngredientsAtFirst();

}
