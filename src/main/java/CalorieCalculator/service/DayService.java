package CalorieCalculator.service;

import CalorieCalculator.model.service.DayServiceModel;
import CalorieCalculator.model.view.DayViewModel;

import java.util.List;

public interface DayService {
    DayViewModel addDayToUser(DayServiceModel dayServiceModel, String username);
    DayServiceModel findDayById(String dayId);
    List<DayViewModel>listOfAllDays();
    void setTotalCaloriesByDay(String id,double totalCalories);
}
