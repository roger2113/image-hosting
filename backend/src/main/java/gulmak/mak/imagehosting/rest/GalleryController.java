package gulmak.mak.imagehosting.rest;

import gulmak.mak.imagehosting.domain.Gallery;
import gulmak.mak.imagehosting.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("user/{userId}")
public class GalleryController {

    private GalleryService galleryService;

    @Autowired
    public void setGalleryService(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @PostMapping("create_gallery")
    public ResponseEntity<Integer> createGallery(@PathVariable int userId, @RequestBody Gallery galleryInput){
        return new ResponseEntity<Integer>(galleryService.createGallery(galleryInput, userId), HttpStatus.CREATED);
    }

    @PostMapping("{galleryId}/add_images")
    public void addImagesToGallery(@PathVariable int galleryId, @RequestBody Set<Integer> imageIds){
        galleryService.addImages(galleryId, imageIds);
    }

}
