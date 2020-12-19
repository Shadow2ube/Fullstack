package ca.unmined.api.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}
