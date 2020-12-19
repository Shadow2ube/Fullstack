package ca.unmined.api.auth.user;

import ca.unmined.api.auth.ApplicationUser;
import ca.unmined.api.auth.ApplicationUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static java.util.Collections.emptyList;

public class UserDetailsServiceImpl implements UserDetailsService {
    private ApplicationUserRepository applicationUserRepository;

    public UserDetailsServiceImpl(ApplicationUserRepository repo) {
        this.applicationUserRepository = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = applicationUserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(user.getUsername(), user.getPassword(), emptyList());
    }
}
