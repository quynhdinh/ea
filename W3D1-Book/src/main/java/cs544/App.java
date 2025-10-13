/*​​​​​​​​​​​​​​​​​​​​​‌‌​‌​​​​​​‌ See LICENSE.md for the source code license */

package cs544;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import cs544.model.Role;
import cs544.model.User;
import cs544.repository.RoleRepository;
import cs544.repository.UserRepository;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder pEncoder;
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        // Check if users already exist to prevent duplicates on every restart
        if (userRepository.count() == 0) {
            User user1 = new User(null, "admin_db", pEncoder.encode("admin_db"), null);
            User user2 = new User(null, "user_db", pEncoder.encode("user_db"), null);
            userRepository.save(user1);
            userRepository.save(user2);

            Role role1 = new Role(null, "ROLE_ADMIN", user1);
            Role role2 = new Role(null, "ROLE_USER", user1);
            Role role3 = new Role(null, "ROLE_USER", user2);

            roleRepository.save(role1);
            roleRepository.save(role2);
            roleRepository.save(role3);
        }
    }
}

