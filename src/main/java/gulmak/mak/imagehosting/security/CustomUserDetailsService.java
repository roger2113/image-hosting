package gulmak.mak.imagehosting.security;

import gulmak.mak.imagehosting.domain.Role;
import gulmak.mak.imagehosting.domain.User;
import gulmak.mak.imagehosting.repository.UserRepository;
import gulmak.mak.imagehosting.service.UserService;
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

@Service("customUserDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        System.out.println("!!!!!!!!!!!!!!!!+++++++++++++++================");
        User user = userRepository.findByLogin(login);
        if(user == null){
            return new org.springframework.security.core.userdetails.User("",
                    "",
                    true,
                    true,
                    true,
                    true,
                    getAuthoritiesForUknown());
        }
        else{
            System.out.println("!!!!!!!!!!!!!!!!+++++++++++++++================");
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
        System.out.println("!!!!!!!!!!!!!!!!+++++++++++++++================");
        authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        return authorities;
    }

    private Collection<? extends GrantedAuthority> getAuthoritiesForUknown() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }
}
