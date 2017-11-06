package gulmak.mak.imagehosting.service;

import gulmak.mak.imagehosting.domain.Gallery;
import gulmak.mak.imagehosting.domain.ImageElement;
import gulmak.mak.imagehosting.repository.GalleryRepository;
import gulmak.mak.imagehosting.repository.ImageElementRepository;
import kotlin.reflect.jvm.internal.impl.descriptors.EffectiveVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GalleryService {

    private GalleryRepository galleryRepository;
    private UserService userService;
    private ImageElementRepository imageElementRepository;

    @Autowired
    public GalleryService(GalleryRepository galleryRepository, UserService userService, ImageElementRepository imageElementRepository) {
        this.galleryRepository = galleryRepository;
        this.userService = userService;
        this.imageElementRepository = imageElementRepository;
    }

    public Gallery getGallery(int id){
        return galleryRepository.findById(id);
    }

    @Transactional
    public Integer createGallery(Gallery galleryInput, int userId){
        final Gallery gallery = new Gallery();
        gallery.setUser(userService.findUser(userId));
        gallery.setName(galleryInput.getName());
        gallery.setCreated(LocalDateTime.now());
        return galleryRepository.save(gallery);
    }

    @Transactional
    public void addImages(int galleryId, Set<Integer> imageIds){
        Gallery gallery = galleryRepository.findById(galleryId);
        gallery.addImages(new HashSet(imageElementRepository.findByIds(imageIds)));
        galleryRepository.save(gallery);
    }
}
