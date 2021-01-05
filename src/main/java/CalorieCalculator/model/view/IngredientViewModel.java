package CalorieCalculator.model.view;

public class IngredientViewModel {
    private String id;
    private String name;
    private String photoUrl;
    private double grams;
    private double caloriePer100grams;
    private double totalCalories;
    public IngredientViewModel() {
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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
