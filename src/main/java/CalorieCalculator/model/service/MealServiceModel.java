package CalorieCalculator.model.service;

import java.util.List;

public class MealServiceModel {
    private String id;
    private String name;
    private PhotoServiceModel photoServiceModel;
    private String photoUrl;
    private String dayId;
    List<SearchAndAddIngredientServiceModel>ingredients;
    public MealServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PhotoServiceModel getPhotoServiceModel() {
        return photoServiceModel;
    }

    public void setPhotoServiceModel(PhotoServiceModel photoServiceModel) {
        this.photoServiceModel = photoServiceModel;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

    public List<SearchAndAddIngredientServiceModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<SearchAndAddIngredientServiceModel> ingredients) {
        this.ingredients = ingredients;
    }
}
