package CalorieCalculator.web;

import CalorieCalculator.model.bindings.DayAddBindingModel;
import CalorieCalculator.model.service.DayServiceModel;

import CalorieCalculator.model.view.DayViewModel;
import CalorieCalculator.service.DayService;
import CalorieCalculator.service.MealService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/day")
public class DayController {
    private final DayService dayService;
    private final ModelMapper modelMapper;
    private final MealService mealService;
@Autowired
    public DayController(DayService dayService, ModelMapper modelMapper, MealService mealService) {
        this.dayService = dayService;
        this.modelMapper = modelMapper;
    this.mealService = mealService;
}

    @GetMapping("/add")
    String addDay(@RequestParam("username")String username, Model model){
        model.addAttribute("dayAddBindingModel",new DayAddBindingModel());
        model.addAttribute("username",username);
        return "add-day";
    }
    @PostMapping("/add")
    ModelAndView addConfirmDay(@RequestParam("username")String username, @Valid @ModelAttribute("dayAddBindingModel")
                          DayAddBindingModel dayAddBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes, ModelAndView modelAndView){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("dayAddBindingModel",dayAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.dayAddBindingModel",
                    bindingResult);
            modelAndView.setViewName("add-day");
            return modelAndView;
        }
        DayViewModel dayViewModel=this.dayService.addDayToUser(this.modelMapper.map(dayAddBindingModel, DayServiceModel.class),username);
        modelAndView.setViewName("view-day");
        double totalCalories=this.mealService.getTotalCaloriesByDayId(dayViewModel.getId());
        dayViewModel.setTotalCalories(totalCalories);
        modelAndView.addObject("day",dayViewModel);
        modelAndView.addObject("mealList",this.mealService.getMealsByDay(dayViewModel.getId()));
        return modelAndView;
    }
    @GetMapping("/view-day")
    String viewDays(@RequestParam("id")String id,Model model) {
        DayServiceModel dayServiceModel=this.dayService.findDayById(id);
        DayViewModel dayViewModel=this.modelMapper.map(dayServiceModel,DayViewModel.class);
        dayViewModel.setDate(dayServiceModel.getDate().toString());
        double totalCalories=this.mealService.getTotalCaloriesByDayId(dayViewModel.getId());
        dayViewModel.setTotalCalories(totalCalories);
        model.addAttribute("day",dayViewModel);
        model.addAttribute("mealList",this.mealService.getMealsByDay(dayViewModel.getId()));
        return "view-day";
    }


}
