package CalorieCalculator.service.impl;

import CalorieCalculator.model.service.CalculatorServiceModel;
import CalorieCalculator.model.view.CalculatorViewModel;
import CalorieCalculator.model.view.UserViewModel;
import CalorieCalculator.service.CalculatorService;
import CalorieCalculator.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CalculatorServiceImpl implements CalculatorService {
    private final UserService userService;

    public CalculatorServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CalculatorViewModel calculateCalories(CalculatorServiceModel calculatorServiceModel, String username) {
        int age=calculatorServiceModel.getAge();
        double weight=calculatorServiceModel.getWeight();
        double height=calculatorServiceModel.getHeight();
        double resultFromFormula=0;
        double caloriesBasedOfExercise=0;
        double totalCalories=0;
        if(calculatorServiceModel.getGender().toLowerCase().equals("female")){
            resultFromFormula =  Math.round((9.99*weight)+(6.25*height)-(4.92*age)-161);
        }
        if(calculatorServiceModel.getGender().toLowerCase().equals("male")){
            resultFromFormula =  Math.round((9.99*weight)+(6.25*height)-(4.92*age)+5);
        }
        if(calculatorServiceModel.getExerciseLevel()==1){
            caloriesBasedOfExercise=resultFromFormula;
        }else if(calculatorServiceModel.getExerciseLevel()==2){
            caloriesBasedOfExercise=resultFromFormula*1.2;
        }else if(calculatorServiceModel.getExerciseLevel()==3){
            caloriesBasedOfExercise=resultFromFormula*1.35;
        }else if(calculatorServiceModel.getExerciseLevel()==4){
            caloriesBasedOfExercise=resultFromFormula*1.55;
        }else if(calculatorServiceModel.getExerciseLevel()==5){
             caloriesBasedOfExercise=resultFromFormula*1.75;
        }else if(calculatorServiceModel.getExerciseLevel()==6){
             caloriesBasedOfExercise=resultFromFormula*1.95;
        }
        double maintain=Math.round(caloriesBasedOfExercise);
        double loss=Math.round(caloriesBasedOfExercise*0.80);
        double gain=Math.round(caloriesBasedOfExercise*1.20);;
        CalculatorViewModel calculatorViewModel = new CalculatorViewModel();
        calculatorViewModel.setGainCalories(gain);
        calculatorViewModel.setLossCalories(loss);
        calculatorViewModel.setMaintainCalories(maintain);
        return calculatorViewModel;
    }
}
