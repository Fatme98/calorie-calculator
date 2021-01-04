package CalorieCalculator.model.bindings;

import javax.validation.constraints.Min;

public class IngredientAddBindingModel {
    private String name;
    private double grams;
    private double caloriePer100grams;

    public IngredientAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Min(value=0,message = "Cannot be less then 0")
    public double getGrams() {
        return grams;
    }

    public void setGrams(double grams) {
        this.grams = grams;
    }
    @Min(value=0,message = "Cannot be less then 0")
    public double getCaloriePer100grams() {
        return caloriePer100grams;
    }

    public void setCaloriePer100grams(double caloriePer100grams) {
        this.caloriePer100grams = caloriePer100grams;
    }
}
