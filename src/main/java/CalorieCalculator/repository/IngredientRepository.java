package CalorieCalculator.repository;

import CalorieCalculator.model.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,String> {
    List<Ingredient> findByMeal_Id(String id);
    List<Ingredient> findByName(String name);
}
