package CalorieCalculator.model.view;

public class CalculatorViewModel {
    private double maintainCalories;
    private double lossCalories;
    private double gainCalories;

    public CalculatorViewModel() {
    }

    public double getMaintainCalories() {
        return maintainCalories;
    }

    public void setMaintainCalories(double maintainCalories) {
        this.maintainCalories = maintainCalories;
    }

    public double getLossCalories() {
        return lossCalories;
    }

    public void setLossCalories(double lossCalories) {
        this.lossCalories = lossCalories;
    }

    public double getGainCalories() {
        return gainCalories;
    }

    public void setGainCalories(double gainCalories) {
        this.gainCalories = gainCalories;
    }
}
