package CalorieCalculator.service.impl;

import CalorieCalculator.model.entity.Photo;
import CalorieCalculator.model.service.PhotoProfileServiceModel;
import CalorieCalculator.model.service.PhotoServiceModel;
import CalorieCalculator.repository.PhotoRepository;
import CalorieCalculator.service.PhotoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;
    private final ModelMapper modelMapper;
    public static final String UPLOAD_DIR="images";
    public PhotoServiceImpl(PhotoRepository photoRepository, ModelMapper modelMapper) {

        this.photoRepository = photoRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public PhotoProfileServiceModel addPhoto(PhotoServiceModel photoServiceModel) {
        Photo photo=this.modelMapper.map(photoServiceModel,Photo.class);
        if(photoServiceModel.getFile()!=null){
            String name=photoServiceModel.getFile().getOriginalFilename();
            long size=photoServiceModel.getFile().getSize();
            try{
                File currentDir=new File(UPLOAD_DIR);
                if(!currentDir.exists()){
                    currentDir.mkdirs();
                }
                String path=currentDir.getAbsolutePath()+"/"+photoServiceModel.getFile().getOriginalFilename();
                path=new File(path).getAbsolutePath();
                File f=new File(path);
                FileCopyUtils.copy(photoServiceModel.getFile().getInputStream(),new FileOutputStream(f));

            } catch (IOException e) {
                e.printStackTrace();
            }
            String path1="/"+UPLOAD_DIR+"/"+photoServiceModel.getFile().getOriginalFilename();
            photo.setImageUrl(path1);
        }

        PhotoProfileServiceModel photoProfileServiceModel=new PhotoProfileServiceModel();
        photo=this.photoRepository.saveAndFlush(photo);
        photoProfileServiceModel.setPhoto(photo);
        return photoProfileServiceModel;
    }

    @Override
    public PhotoProfileServiceModel getPhoto(String photoUrl) {
        PhotoProfileServiceModel photoProfileServiceModel=new PhotoProfileServiceModel();
        Photo photo=this.photoRepository.findByImageUrl(photoUrl);
        photoProfileServiceModel.setPhoto(photo);
        return photoProfileServiceModel;
    }

    @Override
    public PhotoProfileServiceModel addPhotoStatic(String imageUrl) {
        Photo photo1=new Photo();
        photo1.setImageUrl(imageUrl);
        Photo photo=this.photoRepository.saveAndFlush(photo1);
        PhotoProfileServiceModel photoProfileServiceModel=new PhotoProfileServiceModel();
        photoProfileServiceModel.setPhoto(photo);
        return photoProfileServiceModel;
    }
}
