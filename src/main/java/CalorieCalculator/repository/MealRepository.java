package CalorieCalculator.repository;

import CalorieCalculator.model.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal,String> {
    List<Meal> findByDay_Id(String id);
}
