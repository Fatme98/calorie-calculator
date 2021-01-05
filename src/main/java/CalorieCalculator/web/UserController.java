package CalorieCalculator.web;

import CalorieCalculator.model.bindings.UserLoginBindingModel;
import CalorieCalculator.model.bindings.UserRegisterBindingModel;
import CalorieCalculator.model.service.PhotoServiceModel;
import CalorieCalculator.model.service.UserDetailsServiceModel;
import CalorieCalculator.model.service.UserServiceModel;
import CalorieCalculator.service.DayService;
import CalorieCalculator.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final DayService dayService;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, DayService dayService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.dayService = dayService;
    }


    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@RequestParam("img") MultipartFile file,
                                  @Valid @ModelAttribute("userRegisterBindingModel")
                                          UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, HttpSession httpSession, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors() || !
                userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.
                    addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                            bindingResult);
            return "register";
        }
        if(userService.existsUser(userRegisterBindingModel.getUsername())){
            bindingResult.rejectValue
                    ("username","error.username","An account with this username already exists.");
            return "register";
        }
        PhotoServiceModel photoServiceModel=new PhotoServiceModel();
        photoServiceModel.setFile(file);
        UserServiceModel userServiceModel=this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        userServiceModel.setPhotoServiceModel(photoServiceModel);
        this.userService.createAndLoginUser(userServiceModel);

        model.addAttribute("name",httpSession.getAttribute("application-name"));

        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid @ModelAttribute("userLoginBindingModel")
                                       UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                    bindingResult);
            return "login";
        }
        UserDetailsServiceModel user=this.userService.findUserByUsername(userLoginBindingModel.getUsername());
        if (user == null) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                    bindingResult);
            return "login";
        }


        this.userService.loginUser(user.getUsername(),userLoginBindingModel.getPassword());
        return "redirect:/user/full-statistics";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }


    @GetMapping("/full-statistics")
    String getFullStatistics(Model model, @AuthenticationPrincipal Principal principal){
        model.addAttribute("username",principal.getName());
        model.addAttribute("user",this.userService.getUser(principal.getName()));
        model.addAttribute("dayList", this.dayService.listOfAllDays());
        return "full-statistics";
    }
}
