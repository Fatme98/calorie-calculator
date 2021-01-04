package CalorieCalculator.model.entity;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;

@Entity
@Table(name="ingredients")
@Transactional
public class Ingredient extends BaseEntity{
    private String name;
    private Meal meal;
    private Photo photo;
    private double grams;
    private double caloriePer100grams;
    private double totalCalories;
    public Ingredient() {
    }
    @Column(name="name",nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
    @OneToOne
    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
    @Column(name="grams",nullable = false)
    @Min(value=0,message = "Cannot be less then 0")
    public double getGrams() {
        return grams;
    }

    public void setGrams(double grams) {
        this.grams = grams;
    }
    @Column(name="calore_per_100_grams",nullable = false)
    @Min(value=0,message = "Cannot be less then 0")
    public double getCaloriePer100grams() {
        return caloriePer100grams;
    }

    public void setCaloriePer100grams(double caloriePer100grams) {
        this.caloriePer100grams = caloriePer100grams;
    }
    @Column(name="total_calories",nullable = false)
    @Min(value=0,message = "Cannot be less then 0")
    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }
}
