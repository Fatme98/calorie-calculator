package CalorieCalculator.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Table(name="photos")
@Transactional
public class Photo extends BaseEntity{
    private String imageUrl;
    public Photo() {
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
