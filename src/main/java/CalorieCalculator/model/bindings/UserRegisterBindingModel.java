package CalorieCalculator.model.bindings;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UserRegisterBindingModel {
    private String username;
    private String password;
    private String confirmPassword;
    private int age;

    public UserRegisterBindingModel() {
    }
    @NotBlank
    @Length(min=3,message = "Username must be more than 3 symbols")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotBlank
    @Length(min=3,message = "Password must be more than 3 symbols")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
@Min(value=0,message = "Age must be positive number")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
