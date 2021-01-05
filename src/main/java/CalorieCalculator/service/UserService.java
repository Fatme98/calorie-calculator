package CalorieCalculator.service;

import CalorieCalculator.model.entity.UserEntity;
import CalorieCalculator.model.service.UserDetailsServiceModel;
import CalorieCalculator.model.service.UserServiceModel;
import CalorieCalculator.model.view.UserViewModel;

import java.util.List;

public interface UserService {

    boolean existsUser(String username);
    UserEntity getOrCreateUser(UserServiceModel userServiceModel);
    void createAndLoginUser(UserServiceModel userServiceModel);
    void loginUser(String username,String password);
    boolean isAdmin(String username);
    void createAdmin();
    UserDetailsServiceModel findUserByUsername(String username);
    UserViewModel getUser(String username);

}
