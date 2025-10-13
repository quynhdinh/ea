package cs544.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import cs544.model.Role;
public interface RoleRepository extends JpaRepository<Role, Long> {
}