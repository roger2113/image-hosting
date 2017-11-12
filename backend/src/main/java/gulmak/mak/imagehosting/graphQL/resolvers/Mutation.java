package gulmak.mak.imagehosting.graphQL.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import gulmak.mak.imagehosting.domain.User;
import gulmak.mak.imagehosting.service.GalleryService;
import gulmak.mak.imagehosting.service.ImageElementService;
import gulmak.mak.imagehosting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    private UserService userService;
    private GalleryService galleryService;
    private ImageElementService imageElementService;

    @Autowired
    public Mutation(UserService userService, GalleryService galleryService, ImageElementService imageElementService) {
        this.userService = userService;
        this.galleryService = galleryService;
        this.imageElementService = imageElementService;
    }

    public User createUser(String login, String email, String password){
        return new User();
    }

    public void uploadImage(String name){

    }
}
