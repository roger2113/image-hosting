package gulmak.mak.imagehosting.graphQL.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;
import gulmak.mak.imagehosting.domain.ImageElement;
import gulmak.mak.imagehosting.domain.User;
import gulmak.mak.imagehosting.service.GalleryService;
import gulmak.mak.imagehosting.service.ImageElementService;
import gulmak.mak.imagehosting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    private UserService userService;
    private GalleryService galleryService;
    private ImageElementService imageElementService;

    @Autowired
    public Query(UserService userService, GalleryService galleryService, ImageElementService imageElementService) {
        this.userService = userService;
        this.galleryService = galleryService;
        this.imageElementService = imageElementService;
    }

    public User user(int id){
        return userService.findUser(id);
    }

    public List<User> users(){
        return Lists.newArrayList(userService.findAll());
    }

    public ImageElement image(int id){
        return imageElementService.findImage(id);
    }
}
