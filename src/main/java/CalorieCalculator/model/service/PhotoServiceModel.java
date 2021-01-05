package CalorieCalculator.model.service;

import org.springframework.web.multipart.MultipartFile;

public class PhotoServiceModel {
    private MultipartFile file;
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }


}
