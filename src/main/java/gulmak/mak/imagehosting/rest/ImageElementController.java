package gulmak.mak.imagehosting.rest;

import gulmak.mak.imagehosting.domain.ImageElement;
import gulmak.mak.imagehosting.service.ImageElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ImageElementController {

    private ImageElementService imageElementService;

    @Autowired
    public void setImageElementService(ImageElementService imageElementService) {
        this.imageElementService = imageElementService;
    }

    @GetMapping("image/{id}")
    public ResponseEntity<ImageElement> getImage(@PathVariable int id){
        return new ResponseEntity<>(imageElementService.findImage(id), HttpStatus.OK);
    }

    @GetMapping("user/{userId}/{galleryId}")
    public ResponseEntity<List<ImageElement>> getGalleryThumbnails(@PathVariable int galleryId){
        return new ResponseEntity<>(imageElementService.findGalleryThumbnails(galleryId), HttpStatus.OK);
    }

    @GetMapping("user/{userId}/images")
    public ResponseEntity<List<ImageElement>> getUserThumbnails(@PathVariable int userId){
        return new ResponseEntity<>(imageElementService.findUserThumbnails(userId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "user/{userId}/upload_image",
            consumes = "multipart/form-data",
            method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestParam("file") MultipartFile imageInput, @PathVariable int userId) {
        try {
            imageElementService.saveToImageElement(imageInput, userId);
        }
        catch (IOException e){

        }
        return new ResponseEntity<>( HttpStatus.CREATED);
    }



}
