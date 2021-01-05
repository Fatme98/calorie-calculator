package CalorieCalculator.model.service;

public class UserServiceModel {
    private String username;
    private String password;
    private int age;
    private PhotoServiceModel photoServiceModel;

    public UserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PhotoServiceModel getPhotoServiceModel() {
        return photoServiceModel;
    }

    public void setPhotoServiceModel(PhotoServiceModel photoServiceModel) {
        this.photoServiceModel = photoServiceModel;
    }
}
