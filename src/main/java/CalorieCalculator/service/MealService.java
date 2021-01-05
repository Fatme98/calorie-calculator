package CalorieCalculator.service;

import CalorieCalculator.model.service.MealServiceModel;
import CalorieCalculator.model.service.SearchAndAddIngredientServiceModel;
import CalorieCalculator.model.view.IngredientViewModel;
import CalorieCalculator.model.view.MealViewModel;

import java.util.List;

public interface MealService {
    MealViewModel addMeal(MealServiceModel mealServiceModel, String dayId);
    MealServiceModel findMealById(String id);
    List<MealViewModel>getMealsByDay(String id);
    double getTotalCaloriesByDayId(String dayId);
    void setTotalCaloriesForMealById(String id,double totalCalories);

}
