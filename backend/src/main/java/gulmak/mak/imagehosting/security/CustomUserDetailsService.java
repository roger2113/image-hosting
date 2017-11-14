package gulmak.mak.imagehosting.security;

import gulmak.mak.imagehosting.common.error.UserNotFoundException;
import gulmak.mak.imagehosting.domain.Role;
import gulmak.mak.imagehosting.domain.User;
import gulmak.mak.imagehosting.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        logger.info(String.format("Authenticating user with login:%s", login));
        User user = userRepository.findByLogin(login).orElseThrow(() -> new UserNotFoundException());
        if(user == null){
            logger.info(String.format("User with login %s hasn't been found", login));
            return new org.springframework.security.core.userdetails.User("",
                    "",
                    true,
                    true,
                    true,
                    true,
                    getAuthoritiesForUknown());
        }
        else{
            logger.info(String.format("User with login %s has been found", login));
            return new org.springframework.security.core.userdetails.User(user.getLogin(),
                    user.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    getGrantedAuthorities(user.getRole()));
        }
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(Role role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        return authorities;
    }

    private Collection<? extends GrantedAuthority> getAuthoritiesForUknown() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }
}
