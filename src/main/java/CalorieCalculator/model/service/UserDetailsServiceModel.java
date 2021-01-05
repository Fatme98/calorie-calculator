package CalorieCalculator.model.service;

public class UserDetailsServiceModel {
    private String id;
    private String username;
    private String password;
    private int age;
    private PhotoProfileServiceModel photoProfileServiceModel;
    private String wantedEffect;
    private double totalCalories;

    public UserDetailsServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public PhotoProfileServiceModel getPhotoProfileServiceModel() {
        return photoProfileServiceModel;
    }

    public void setPhotoProfileServiceModel(PhotoProfileServiceModel photoProfileServiceModel) {
        this.photoProfileServiceModel = photoProfileServiceModel;
    }

    public String getWantedEffect() {
        return wantedEffect;
    }

    public void setWantedEffect(String wantedEffect) {
        this.wantedEffect = wantedEffect;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }
}
