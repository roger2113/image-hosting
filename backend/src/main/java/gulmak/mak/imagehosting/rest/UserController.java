package gulmak.mak.imagehosting.rest;

import gulmak.mak.imagehosting.domain.User;
import gulmak.mak.imagehosting.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public ResponseEntity<String> home(){
        return new ResponseEntity<>("HOME IMAGEHOSTING PAGE", HttpStatus.OK);
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers(){
        logger.info("Fetching all users list");
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id, Principal principal){
        logger.info(String.format("Fetching user with id:%s", id));
        User user = userService.findUser(id);
        if (user.getLogin().equals(principal.getName())){
            return new ResponseEntity<>(userService.findUser(id), HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.LOCKED);
    }

    @PostMapping("registration")
    public ResponseEntity<Integer> createUser(@RequestBody User userInput){
        return new ResponseEntity<Integer>(userService.createUser(userInput), HttpStatus.CREATED);
    }

    @DeleteMapping("user/{id}/delete")
    public void deleteUser(@PathVariable int id){
        logger.info(String.format("Deleting user with id:%s", id));
        userService.deleteUser(id);
    }

}
