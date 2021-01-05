package CalorieCalculator.web;

import CalorieCalculator.model.bindings.CalculatorBindingModel;
import CalorieCalculator.model.service.CalculatorServiceModel;
import CalorieCalculator.model.view.CalculatorViewModel;
import CalorieCalculator.service.CalculatorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/calculator")
public class CalculatorController {
    private final CalculatorService calculatorService;
    private final ModelMapper modelMapper;
    public CalculatorController(CalculatorService calculatorService, ModelMapper modelMapper) {
        this.calculatorService = calculatorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/calculate")
    String getCalculator(@RequestParam("username")String username, Model model){
        model.addAttribute("calculatorBindingModel",new CalculatorBindingModel());
        model.addAttribute("username",username);
        return "calculate-daily-calories";
    }
    @PostMapping("/calculate")
    String calculate(@RequestParam("username")String username, @Valid @ModelAttribute("calculatorBindingModel")
            CalculatorBindingModel calculatorBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("calculatorBindingModel",calculatorBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.calculatorBindingModel",
                    bindingResult);

            return "calculate-daily-calories";
        }
        CalculatorServiceModel calculatorServiceModel=this.modelMapper.map(calculatorBindingModel,CalculatorServiceModel.class);
        CalculatorViewModel calculatorViewModel= this.calculatorService.calculateCalories(calculatorServiceModel,username);
        model.addAttribute("calculatorViewModel",calculatorViewModel);
        return "calorie-result";
    }

}
