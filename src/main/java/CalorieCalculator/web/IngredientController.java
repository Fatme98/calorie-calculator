package CalorieCalculator.web;

import CalorieCalculator.model.bindings.IngredientAddBindingModel;
import CalorieCalculator.model.bindings.SearchIngredientBindingModel;
import CalorieCalculator.model.service.IngredientServiceModel;
import CalorieCalculator.model.service.MealServiceModel;
import CalorieCalculator.model.service.PhotoServiceModel;
import CalorieCalculator.model.view.IngredientViewModel;
import CalorieCalculator.model.view.MealViewModel;
import CalorieCalculator.service.IngredientService;
import CalorieCalculator.service.MealService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {
    private final ModelMapper modelMapper;
    private final IngredientService ingredientService;
    private final MealService mealService;
@Autowired
    public IngredientController(ModelMapper modelMapper, IngredientService ingredientService, MealService mealService) {
        this.modelMapper = modelMapper;
        this.ingredientService = ingredientService;
    this.mealService = mealService;
}

    @GetMapping("/add")
    String addIngredient(@RequestParam("id")String id,Model model){
        model.addAttribute("ingredientAddBindingModel",new IngredientAddBindingModel());
        model.addAttribute("id", id);
        return "add-ingredient";
    }
    @PostMapping("/add")
    String addConfirmIngredient(@RequestParam("id")String id, @RequestParam("img") MultipartFile file,@Valid @ModelAttribute("ingredientAddBindingModel")
                          IngredientAddBindingModel ingredientAddBindingModel,
                                BindingResult bindingResult, HttpSession httpSession, RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("ingredientAddBindingModel", ingredientAddBindingModel);
            redirectAttributes.
                    addFlashAttribute("org.springframework.validation.BindingResult.ingredientAddBindingModel",
                            bindingResult);
            return "add-ingredient";
        }

        PhotoServiceModel photoServiceModel=new PhotoServiceModel();
        photoServiceModel.setFile(file);
        IngredientServiceModel ingredientServiceModel =this.modelMapper.map(ingredientAddBindingModel, IngredientServiceModel.class);
        ingredientServiceModel.setPhotoServiceModel(photoServiceModel);
        this.ingredientService.addIngredient(ingredientServiceModel,id);
        MealServiceModel mealServiceModel = this.mealService.findMealById(id);
        MealViewModel mealViewModel=this.modelMapper.map(mealServiceModel,MealViewModel.class);
        double totalCalories=this.ingredientService.totalCaloriesForMealById(mealViewModel.getId());
        this.mealService.setTotalCaloriesForMealById(mealViewModel.getId(),totalCalories);
        mealViewModel.setTotalCalories(totalCalories);

        model.addAttribute("id",mealViewModel.getId());
        model.addAttribute("meal", mealViewModel);
        model.addAttribute("ingredientList",this.ingredientService.findAllIngredientsByMealId(mealViewModel.getId()));
        return "view-meal";
    }
    @GetMapping("/search")
    String searchIngredient(@RequestParam("id")String id,Model model){
        model.addAttribute("searchIngredientBindingModel",new SearchIngredientBindingModel());
        model.addAttribute("id", id);
        return "search-ingredient";
    }
    @PostMapping("/search")
    String searchConfirmIngredient(@RequestParam("id")String id,@Valid @ModelAttribute("searchIngredientBindingModel")
                                  SearchIngredientBindingModel searchIngredientBindingModel,
                                BindingResult bindingResult, HttpSession httpSession, RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("searchIngredientBindingModel", searchIngredientBindingModel);
            redirectAttributes.
                    addFlashAttribute("org.springframework.validation.BindingResult.searchIngredientBindingModel",
                            bindingResult);
            return "search-ingredient";
        }
        IngredientViewModel ingredientViewModel=this.ingredientService.
                searchAndAddToMeal(searchIngredientBindingModel.getName(),id,searchIngredientBindingModel.getGrams());
        model.addAttribute("id",id);
        model.addAttribute("ingredient",ingredientViewModel);
        return "list-ingredients";
    }

}
