package CalorieCalculator.model.service;

public class IngredientServiceModel {
    private String id;
    private String name;
    private PhotoServiceModel photoServiceModel;
    private double grams;
    private double caloriePer100grams;
    private double totalCalories;

    public IngredientServiceModel() {
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

    public double getGrams() {
        return grams;
    }

    public void setGrams(double grams) {
        this.grams = grams;
    }

    public double getCaloriePer100grams() {
        return caloriePer100grams;
    }

    public void setCaloriePer100grams(double caloriePer100grams) {
        this.caloriePer100grams = caloriePer100grams;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }
}
