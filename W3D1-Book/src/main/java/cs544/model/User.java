package cs544.model;

import lombok.*;
import jakarta.persistence.*;
import java.util.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Users") // Using "Users" to avoid potential SQL keyword conflicts with "User"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @OneToMany(mappedBy = "user") // The 'user' field in the Role entity maps this relationship
    private List<Role> roles = new ArrayList<>();

    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public String[] getRoleNames() {
        return roles.stream().map(Role::getRoleName).toArray(String[]::new);
    }
}