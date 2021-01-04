package CalorieCalculator.repository;

import CalorieCalculator.model.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,String> {
    Photo findByImageUrl(String imageUrl);
}
