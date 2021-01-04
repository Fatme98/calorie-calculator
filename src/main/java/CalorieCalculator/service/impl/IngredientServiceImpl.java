package CalorieCalculator.service.impl;

import CalorieCalculator.error.IngredientNotFoundException;
import CalorieCalculator.model.entity.Ingredient;
import CalorieCalculator.model.entity.Meal;
import CalorieCalculator.model.entity.Photo;
import CalorieCalculator.model.service.IngredientServiceModel;
import CalorieCalculator.model.service.MealServiceModel;
import CalorieCalculator.model.service.PhotoProfileServiceModel;
import CalorieCalculator.model.service.SearchAndAddIngredientServiceModel;
import CalorieCalculator.model.view.IngredientViewModel;
import CalorieCalculator.repository.IngredientRepository;
import CalorieCalculator.service.IngredientService;
import CalorieCalculator.service.MealService;
import CalorieCalculator.service.PhotoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {
    private final ModelMapper modelMapper;
    private final IngredientRepository ingredientRepository;
    private final MealService mealService;
    private final PhotoService photoService;
@Autowired
    public IngredientServiceImpl(ModelMapper modelMapper, IngredientRepository ingredientRepository, MealService mealService, PhotoService photoService) {
        this.modelMapper = modelMapper;
        this.ingredientRepository = ingredientRepository;
        this.mealService = mealService;
        this.photoService = photoService;
}

    @Override
    public void addIngredient(IngredientServiceModel ingredientServiceModel, String mealId) {
        Ingredient ingredient=this.modelMapper.map(ingredientServiceModel,Ingredient.class);
        PhotoProfileServiceModel photoProfileServiceModel=this.photoService.
                addPhoto(ingredientServiceModel.getPhotoServiceModel());
        ingredient.setPhoto(photoProfileServiceModel.getPhoto());
        MealServiceModel mealServiceModel=this.mealService.findMealById(mealId);
        Meal meal=new Meal();
        meal.setName(mealServiceModel.getName());
        meal.setId(mealServiceModel.getId());
        ingredient.setMeal(meal);
        double totalCalories=ingredientServiceModel.getCaloriePer100grams()*(ingredientServiceModel.getGrams()/100.0);
        ingredient.setTotalCalories(totalCalories);
        this.ingredientRepository.saveAndFlush(ingredient);
    }

    @Override
    public List<IngredientViewModel> findAllIngredientsByMealId(String id) {
        List<Ingredient> ingredients=this.ingredientRepository.findByMeal_Id(id);
        List<IngredientViewModel>ingredientViewModels=new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            IngredientViewModel ingredientViewModel=new IngredientViewModel();
            ingredientViewModel.setId(ingredient.getId());
            ingredientViewModel.setName(ingredient.getName());
            ingredientViewModel.setPhotoUrl(ingredient.getPhoto().getImageUrl());
            ingredientViewModel.setCaloriePer100grams(ingredient.getCaloriePer100grams());
            ingredientViewModel.setGrams(ingredient.getGrams());
            ingredientViewModel.setTotalCalories(ingredient.getTotalCalories());
            ingredientViewModels.add(ingredientViewModel);
        }
        return ingredientViewModels;
    }

    @Override
    public double totalCaloriesForMealById(String mealId) {
        List<Ingredient> ingredients=this.ingredientRepository.findByMeal_Id(mealId);
        double totalCalories=0;
        for (Ingredient ingredient : ingredients) {
            totalCalories+=ingredient.getTotalCalories();
        }
        return totalCalories;
    }

    @Override
    public IngredientViewModel searchAndAddToMeal(String name,String mealId,double grams) {
        List<Ingredient> ingredient=this.ingredientRepository.findByName(name);
        if(ingredient.size()<=0){
            throw new IngredientNotFoundException(name);
        }
        IngredientViewModel ingredientViewModel=new IngredientViewModel();
        ingredientViewModel.setName(ingredient.get(0).getName());
        ingredientViewModel.setPhotoUrl(ingredient.get(0).getPhoto().getImageUrl());
        SearchAndAddIngredientServiceModel searchAndAddIngredientServiceModel=new SearchAndAddIngredientServiceModel();
        searchAndAddIngredientServiceModel.setId(ingredient.get(0).getId());
        searchAndAddIngredientServiceModel.setCaloriePer100grams(ingredient.get(0).getCaloriePer100grams());
        searchAndAddIngredientServiceModel.setName(ingredient.get(0).getName());
        searchAndAddIngredientServiceModel.setPhotoUrl(ingredient.get(0).getPhoto().getImageUrl());
        searchAndAddIngredientServiceModel.setGrams(grams);
        double totalCalories=ingredient.get(0).getCaloriePer100grams()*(grams/100.0);
        searchAndAddIngredientServiceModel.setTotalCalories(totalCalories);
        ingredientViewModel.setTotalCalories(totalCalories);
        MealServiceModel mealServiceModel=this.mealService.findMealById(mealId);
        Meal meal = this.modelMapper.map(mealServiceModel,Meal.class);
        Ingredient ingredient1=new Ingredient();
        ingredient1.setName(ingredient.get(0).getName());
        ingredient1.setMeal(meal);
        ingredient1.setTotalCalories(totalCalories);
        ingredient1.setGrams(grams);
        ingredient1.setPhoto(ingredient.get(0).getPhoto());
        ingredient1.setCaloriePer100grams(ingredient.get(0).getCaloriePer100grams());
        this.ingredientRepository.saveAndFlush(ingredient1);
        return ingredientViewModel;
    }

    @Override
    public void addIngredientsAtFirst() {
        if(this.ingredientRepository.count()==0){
            Ingredient tomato=new Ingredient();
            tomato.setName("Tomato");
            tomato.setCaloriePer100grams(100);
            PhotoProfileServiceModel photoProfileServiceModel=this.photoService.addPhotoStatic("/images/tomato.jpg");
            tomato.setPhoto(photoProfileServiceModel.getPhoto());
            Ingredient friedMiceBall=new Ingredient();
            friedMiceBall.setName("Fried MiceBalls");
            friedMiceBall.setCaloriePer100grams(300);
            PhotoProfileServiceModel photoProfileServiceModel1=this.photoService.addPhotoStatic("/images/friedMiceBalls.jpg");
            friedMiceBall.setPhoto(photoProfileServiceModel1.getPhoto());
            Ingredient fryPotato=new Ingredient();
            fryPotato.setName("Fry Potatoes");
            fryPotato.setCaloriePer100grams(500);
            PhotoProfileServiceModel photoProfileServiceModel3=this.photoService.addPhotoStatic("/images/friedPotatoes.jpg");
            friedMiceBall.setPhoto(photoProfileServiceModel3.getPhoto());
            this.ingredientRepository.saveAndFlush(tomato);
            this.ingredientRepository.saveAndFlush(friedMiceBall);
            this.ingredientRepository.saveAndFlush(fryPotato);
        }
    }

}
