package gulmak.mak.imagehosting.rest;

import gulmak.mak.imagehosting.domain.User;
import gulmak.mak.imagehosting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id){
        return new ResponseEntity<>(userService.findUser(id), HttpStatus.OK);
    }

    @PostMapping(value = "createUser")
    public ResponseEntity<Integer> createUser(@RequestBody User userInput){
        System.out.println(userInput);
        Integer id = userService.createUser(userInput);
        return new ResponseEntity<Integer>(id, HttpStatus.CREATED);
    }

    @DeleteMapping("user/{id}")
    public void deleteUser(@PathVariable int id){

    }

}
