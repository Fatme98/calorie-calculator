package CalorieCalculator.model.bindings;

public class SearchIngredientBindingModel {
    private String name;
    private double grams;

    public SearchIngredientBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrams() {
        return grams;
    }

    public void setGrams(double grams) {
        this.grams = grams;
    }
}
