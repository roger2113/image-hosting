package gulmak.mak.imagehosting.service;

import gulmak.mak.imagehosting.domain.Role;
import gulmak.mak.imagehosting.domain.User;
import gulmak.mak.imagehosting.error.UserAlreadyExistsException;
import gulmak.mak.imagehosting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUser(int id) {
        return userRepository.findById(id);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findUserByLogin(String login){
        return userRepository.findByLogin(login);
    }

    @Transactional
    public Integer createUser(User userInput){
        if(emailExist(userInput.getEmail())){
            throw new UserAlreadyExistsException(String.format("Account with this %s has been already registered", userInput.getEmail()));
        }
        final User user = new User();
        user.setLogin(userInput.getLogin());
        user.setEmail(userInput.getEmail());
        user.setPassword(passwordEncoder.encode(userInput.getPassword()));
        user.setCreated(LocalDateTime.now());
        user.setRole(Role.USER);
        if (userInput.getFirstName()!= null) user.setFirstName(userInput.getFirstName());
        if (userInput.getLastName() != null) user.setLastName(userInput.getLastName());
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(int id){
        userRepository.delete(id);
    }

    private boolean emailExist(String email){
        return userRepository.findByEmail(email) != null;
    }
}
