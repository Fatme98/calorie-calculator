package CalorieCalculator.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Table(name="users")
@Transactional
public class UserEntity extends BaseEntity{
    private String username;
    private String password;
    private int age;
    private Photo profilePicture;
    private List<RoleEntity>roles;

    public UserEntity() {
    }

@Column(name="username", nullable = false, unique = true)
@Length(min=3, message = "The username must be more than 3 symbols.")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Column(name="password", nullable = false)
    @Length(min=3, message = "The password must be more than 3 symbols.")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
@Column(name="age",nullable = false)
@Min(value=0,message = "Age must be positive number!")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @OneToOne
    public Photo getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Photo profilePicture) {
        this.profilePicture = profilePicture;
    }
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="user_id")
    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

}
