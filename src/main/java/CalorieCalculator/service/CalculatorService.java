package CalorieCalculator.service;

import CalorieCalculator.model.service.CalculatorServiceModel;
import CalorieCalculator.model.view.CalculatorViewModel;

public interface CalculatorService {
    CalculatorViewModel calculateCalories(CalculatorServiceModel calculatorServiceModel, String username);

}
