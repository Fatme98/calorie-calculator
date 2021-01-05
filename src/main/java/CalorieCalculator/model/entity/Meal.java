package CalorieCalculator.model.entity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="meals")
@Transactional
public class Meal extends BaseEntity{
    private String name;
    private Photo photo;
    private List<Ingredient> ingredients;
    private Day day;
    private double totalCalories;
    public Meal() {
        ingredients=new ArrayList<>();
    }
@Column(name="name",nullable=false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @OneToMany(mappedBy ="meal", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    @OneToOne
    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
@ManyToOne
    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }
}
