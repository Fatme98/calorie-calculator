package CalorieCalculator.service.impl;

import CalorieCalculator.error.MealNotFoundException;
import CalorieCalculator.model.entity.Day;
import CalorieCalculator.model.entity.Ingredient;
import CalorieCalculator.model.entity.Meal;
import CalorieCalculator.model.entity.UserEntity;
import CalorieCalculator.model.service.*;
import CalorieCalculator.model.view.IngredientViewModel;
import CalorieCalculator.model.view.MealViewModel;
import CalorieCalculator.repository.MealRepository;
import CalorieCalculator.service.DayService;
import CalorieCalculator.service.MealService;
import CalorieCalculator.service.PhotoService;
import CalorieCalculator.service.UserService;
import org.hibernate.dialect.IngresDialect;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;
    private final ModelMapper modelMapper;
    private final PhotoService photoService;
    private final DayService dayService;
    private final UserService userService;
@Autowired
    public MealServiceImpl(MealRepository mealRepository, ModelMapper modelMapper, PhotoService photoService, DayService dayService, UserService userService) {
    this.mealRepository = mealRepository;
    this.modelMapper = modelMapper;
    this.photoService = photoService;
    this.dayService = dayService;
    this.userService = userService;
}

    @Override
    public MealViewModel addMeal(MealServiceModel mealServiceModel, String dayId) {
        Meal meal = new Meal();
        meal = this.modelMapper.map(mealServiceModel,Meal.class);
        PhotoProfileServiceModel photoProfileServiceModel=this.photoService.
                addPhoto(mealServiceModel.getPhotoServiceModel());
        meal.setPhoto(photoProfileServiceModel.getPhoto());
        DayServiceModel dayServiceModel=this.dayService.findDayById(dayId);
        Day day = this.modelMapper.map(dayServiceModel,Day.class);
        UserDetailsServiceModel userDetailsServiceModel = userService.findUserByUsername(dayServiceModel.getUsername());
        UserEntity user = this.modelMapper.map(userDetailsServiceModel,UserEntity.class);
        user.setProfilePicture(userDetailsServiceModel.getPhotoProfileServiceModel().getPhoto());
        day.setUser(user);
        day.setDate(dayServiceModel.getDate());
        meal.setDay(day);
        Meal mealToView = this.mealRepository.saveAndFlush(meal);
        MealViewModel mealViewModel=new MealViewModel();
        mealViewModel.setId(mealToView.getId());
        mealViewModel.setPhotoUrl(mealToView.getPhoto().getImageUrl());
        mealViewModel.setName(mealToView.getName());
        return mealViewModel;
    }

    @Override
    public MealServiceModel findMealById(String id) {
        Meal meal = this.mealRepository.getOne(id);
        if(meal==null){
            throw new MealNotFoundException(meal.getName());
        }
        MealServiceModel mealServiceModel=new MealServiceModel();
        mealServiceModel.setName(meal.getName());
        mealServiceModel.setId(meal.getId());
        mealServiceModel.setPhotoUrl(meal.getPhoto().getImageUrl());
        List<SearchAndAddIngredientServiceModel>ingredientServiceModels=new ArrayList<>();
        if(meal.getIngredients()!=null){
            for (Ingredient ingredient : meal.getIngredients()) {
                SearchAndAddIngredientServiceModel searchAndAddIngredientServiceModel=this.modelMapper
                        .map(ingredient,SearchAndAddIngredientServiceModel.class);
                searchAndAddIngredientServiceModel.setPhotoUrl(ingredient.getPhoto().getImageUrl());
                ingredientServiceModels.add(searchAndAddIngredientServiceModel);
            }
        }
        mealServiceModel.setDayId(meal.getDay().getId());
        mealServiceModel.setIngredients(ingredientServiceModels);
        return mealServiceModel;
    }

    @Override
    public List<MealViewModel> getMealsByDay(String id) {
        List<MealViewModel>meals=new ArrayList<>();
        List<Meal>mealsEntity=this.mealRepository.findByDay_Id(id);
        for (Meal meal : mealsEntity) {
            MealViewModel mealViewModel=new MealViewModel();
            mealViewModel.setId(meal.getId());
            mealViewModel.setName(meal.getName());
            mealViewModel.setPhotoUrl(meal.getPhoto().getImageUrl());
            mealViewModel.setTotalCalories(meal.getTotalCalories());
            meals.add(mealViewModel);
        }
        return meals;
    }

    @Override
    public double getTotalCaloriesByDayId(String dayId) {
        List<Meal>mealsEntity=this.mealRepository.findByDay_Id(dayId);
        double totalCalories=0;
        for (Meal meal : mealsEntity) {
            totalCalories+=meal.getTotalCalories();
        }

        return totalCalories;
    }

    @Override
    public void setTotalCaloriesForMealById(String id,double totalCalories) {
        Meal meal = this.mealRepository.findById(id).orElse(null);
        if(meal!=null){
            meal.setTotalCalories(totalCalories);
            this.mealRepository.save(meal);
            double totalCaloriesByDay=this.getTotalCaloriesByDayId(meal.getDay().getId());
            this.dayService.setTotalCaloriesByDay(meal.getDay().getId(),totalCalories);
        }else{
            throw new MealNotFoundException("Meal not found");
        }


    }



}
