package com.goit.notes.configuration;

import com.goit.notes.entity.Role;
import com.goit.notes.entity.User;
import com.goit.notes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class DbInitConfig {

/*    private final UserRepository repository;

    @Autowired
    public DbInitConfig(UserRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void onApplicationEvent() {

        User user = new User();
        user.setUserName("Admin");
        user.setPassword("$2a$10$NerjWhAeGwlyeDiJ4rl2nuf.4aM4BFSN3Ll5cinNzvBHREOKfTSVG"); //12345
        user.setUserRole(Role.ROLE_ADMIN);
        repository.save(user);
        repository.flush();
    }*/
}
