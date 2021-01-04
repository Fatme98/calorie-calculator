package CalorieCalculator.model.service;

import CalorieCalculator.model.entity.Photo;

public class PhotoProfileServiceModel {
    private Photo photo;

    public PhotoProfileServiceModel() {
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
