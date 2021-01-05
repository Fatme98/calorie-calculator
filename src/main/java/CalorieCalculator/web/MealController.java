package CalorieCalculator.web;

import CalorieCalculator.model.bindings.MealAddBindingModel;
import CalorieCalculator.model.service.MealServiceModel;
import CalorieCalculator.model.service.PhotoServiceModel;
import CalorieCalculator.model.service.SearchAndAddIngredientServiceModel;
import CalorieCalculator.model.view.IngredientViewModel;
import CalorieCalculator.model.view.MealViewModel;
import CalorieCalculator.service.IngredientService;
import CalorieCalculator.service.MealService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("meal")
public class MealController {
    private final ModelMapper modelMapper;
    private final MealService mealService;
    private final IngredientService ingredientService;

    public MealController(ModelMapper modelMapper, MealService mealService, IngredientService ingredientService) {
        this.modelMapper = modelMapper;
        this.mealService = mealService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/add")
    String addMeal(@RequestParam("id")String id, Model model){
        model.addAttribute("mealAddBindingModel",new MealAddBindingModel());
        model.addAttribute("id",id);
        return "add-meal";
    }
    @PostMapping("/add")
    public String addMealConfirm(@RequestParam("id") String id,@RequestParam("img") MultipartFile file,
                                  @Valid @ModelAttribute("mealAddBindingModel")
                                          MealAddBindingModel mealAddBindingModel,
                                  BindingResult bindingResult, HttpSession httpSession, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("mealAddBindingModel", mealAddBindingModel);
            redirectAttributes.
                    addFlashAttribute("org.springframework.validation.BindingResult.mealAddBindingModel",
                            bindingResult);
            return "add-meal";
        }

        PhotoServiceModel photoServiceModel=new PhotoServiceModel();
        photoServiceModel.setFile(file);
        MealServiceModel mealServiceModel =this.modelMapper.map(mealAddBindingModel, MealServiceModel.class);
        mealServiceModel.setPhotoServiceModel(photoServiceModel);
        MealViewModel mealViewModel=this.mealService.addMeal(mealServiceModel,id);
        double totalCalories=this.ingredientService.totalCaloriesForMealById(mealViewModel.getId());
        this.mealService.setTotalCaloriesForMealById(mealViewModel.getId(),totalCalories);
        mealViewModel.setTotalCalories(totalCalories);
        model.addAttribute("id",mealViewModel.getId());
        model.addAttribute("dayId",mealViewModel.getDayId());
        model.addAttribute("meal",mealViewModel);
        return "view-meal";
    }
    @GetMapping("/view-meal")
    String viewMeal(@RequestParam("id")String id,Model model){
        MealServiceModel mealServiceModel = this.mealService.findMealById(id);
        MealViewModel mealViewModel=this.modelMapper.map(mealServiceModel,MealViewModel.class);
        double totalCalories=this.ingredientService.totalCaloriesForMealById(mealViewModel.getId());
        this.mealService.setTotalCaloriesForMealById(mealViewModel.getId(),totalCalories);
        mealViewModel.setTotalCalories(totalCalories);
        model.addAttribute("dayId", mealServiceModel.getDayId());
        model.addAttribute("id",mealViewModel.getId());
        model.addAttribute("meal", mealViewModel);
        model.addAttribute("ingredientList",this.ingredientService.findAllIngredientsByMealId(mealViewModel.getId()));
        return "view-meal";
    }


}
