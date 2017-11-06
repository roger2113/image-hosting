package gulmak.mak.imagehosting.service;

import gulmak.mak.imagehosting.domain.ImageElement;
import gulmak.mak.imagehosting.repository.jpa.ImageElementRepositoryImpl;
import gulmak.mak.imagehosting.util.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImageElementService {

    private ImageElementRepositoryImpl imageElementRepository;
    private UserService userService;

    @Autowired
    public void setImageElementRepository(ImageElementRepositoryImpl imageElementRepository) {
        this.imageElementRepository = imageElementRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ImageElement findImage(int id){
        return imageElementRepository.findById(id);
    }

    public List<ImageElement> findAll(){
        return imageElementRepository.findAll();
    }

    public List<ImageElement> findUserImages(int userId){
        return imageElementRepository.findByUserId(userId);
    }

    public List<ImageElement> findUserThumbnails(int userId){
        return imageElementRepository.findThumbnailsByUserId(userId);
    }

    public List<ImageElement> findGalleryThumbnails(int galleryId){
        return imageElementRepository.findThumbnailsByGalleryId(galleryId);
    }

    @Transactional
    public void saveToImageElement(MultipartFile file, int userId) throws IOException{
        ImageElement imageElement = new ImageElement();
        imageElement.setName(file.getOriginalFilename());
        imageElement.setImage(file.getBytes());
        imageElement.setThumbnail(Images.createThumbnail(file.getBytes(), 200, 200));
        imageElement.setUser(userService.findUser(userId));
        imageElement.setUploaded(LocalDateTime.now());
        imageElementRepository.save(imageElement);
    }

    @Transactional
    public void deleteImageElement(int id){
        imageElementRepository.delete(id);
    }

}
