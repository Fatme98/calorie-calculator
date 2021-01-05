package CalorieCalculator.model.bindings;

public class CalculatorBindingModel {
    private String gender;
    private int age;
    private double weight;
    private double height;
    private int exerciseLevel;
    private String effect;


    public CalculatorBindingModel() {
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getExerciseLevel() {
        return exerciseLevel;
    }

    public void setExerciseLevel(int exerciseLevel) {
        this.exerciseLevel = exerciseLevel;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
}
