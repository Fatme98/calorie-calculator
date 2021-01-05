package CalorieCalculator.service;

import CalorieCalculator.model.service.PhotoProfileServiceModel;
import CalorieCalculator.model.service.PhotoServiceModel;

public interface PhotoService {
     PhotoProfileServiceModel addPhoto(PhotoServiceModel photoServiceModel);
     PhotoProfileServiceModel getPhoto(String photoUrl);
     PhotoProfileServiceModel addPhotoStatic(String imageUrl);
}
