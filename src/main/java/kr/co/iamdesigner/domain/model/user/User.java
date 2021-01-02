package kr.co.iamdesigner.domain.model.user;

import kr.co.iamdesigner.domain.common.model.BaseEntity;
import kr.co.iamdesigner.domain.common.model.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "user")
public class User extends BaseEntity {

    private static final long serialVersionUID = -5108186504017368674L;

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    @Column(name = "email_address", nullable = false, length = 100, unique = true)
    private String emailAddress;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name ="USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
            )
    private Set<Role> roles = new HashSet<>();


    public static User create(String username, String emailAddress, String password) {
        User user = new User();
        user.username = username;
        user.emailAddress = emailAddress;
        user.password = password;
        return user;
    }


    public UserId getUserId() {
        return new UserId(id);
    }

}
