package ua.com.javenier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.com.javenier.entity.User;
import ua.com.javenier.repository.UserRepository;

import java.util.Optional;

@SpringBootApplication
public class MoneyManagerApplication {

    private final UserRepository userRepository;

    public MoneyManagerApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MoneyManagerApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void prepareInitialUser() {
        Optional<User> user = userRepository.findByEmail("user@mail.com");
        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setEmail("user@mail.com");
            newUser.setFirstName("Nikita");
            newUser.setPassword(bCryptPasswordEncoder().encode("rootroot"));
            newUser.setRoles("ROLE_USER");
            newUser.setActive(true);
            userRepository.save(newUser);
        }
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
