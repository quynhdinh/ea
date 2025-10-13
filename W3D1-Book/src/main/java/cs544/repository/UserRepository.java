package cs544.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import cs544.model.User;
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}