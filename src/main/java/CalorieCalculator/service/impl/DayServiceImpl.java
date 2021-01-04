package CalorieCalculator.service.impl;

import CalorieCalculator.model.entity.Day;
import CalorieCalculator.model.entity.UserEntity;
import CalorieCalculator.model.service.DayServiceModel;
import CalorieCalculator.model.service.UserDetailsServiceModel;
import CalorieCalculator.model.view.DayViewModel;
import CalorieCalculator.repository.DayRepository;
import CalorieCalculator.service.DayService;
import CalorieCalculator.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DayServiceImpl implements DayService {
    private final UserService userService;
    private final DayRepository dayRepository;
    private final ModelMapper modelMapper;
@Autowired
    public DayServiceImpl(UserService userService, DayRepository dayRepository, ModelMapper modelMapper) {
        this.userService = userService;
        this.dayRepository = dayRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DayViewModel addDayToUser(DayServiceModel dayServiceModel, String username) {
          Day day=this.modelMapper.map(dayServiceModel,Day.class);
          UserDetailsServiceModel userDetailsServiceModel=this.userService.findUserByUsername(username);
          UserEntity userEntity=this.modelMapper.map(userDetailsServiceModel,UserEntity.class);
          userEntity.setProfilePicture(userDetailsServiceModel.getPhotoProfileServiceModel().getPhoto());
          day.setUser(userEntity);
          day.setDate(LocalDate.now());
          Day dayToView=this.dayRepository.saveAndFlush(day);
          DayViewModel dayViewModel=this.modelMapper.map(dayToView,DayViewModel.class);
          return dayViewModel;
    }

    @Override
    public DayServiceModel findDayById(String dayId) {
        Day day = this.dayRepository.getOne(dayId);
        DayServiceModel dayServiceModel=this.modelMapper.map(day,DayServiceModel.class);
        dayServiceModel.setDate(day.getDate());
        dayServiceModel.setUsername(day.getUser().getUsername());
        return dayServiceModel;
    }

    @Override
    public List<DayViewModel> listOfAllDays() {
        List<DayViewModel>days=new ArrayList<>();
        List<Day>daysEntity=this.dayRepository.findAll();
        daysEntity.forEach(day->{
            DayViewModel dayViewModel=new DayViewModel();
            dayViewModel.setId(day.getId());
            dayViewModel.setName(day.getName());
            dayViewModel.setDate(day.getDate().toString());
            dayViewModel.setTotalCalories(day.getTotalCalories());
            days.add(dayViewModel);
        });
        return days;
    }

    @Override
    public void setTotalCaloriesByDay(String id,double totalCalories) {
        Day day = this.dayRepository.getOne(id);
        day.setTotalCalories(totalCalories);
        this.dayRepository.save(day);
    }


}
