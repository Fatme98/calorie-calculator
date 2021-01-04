package CalorieCalculator.repository;

import CalorieCalculator.model.entity.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayRepository extends JpaRepository<Day,String> {
    List<Day>findAllByUser_Username(String username);
}
