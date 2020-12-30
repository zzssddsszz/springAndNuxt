package kr.co.iamdesigner.domain.model.user;

import kr.co.iamdesigner.domain.common.model.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Table(name = "user")
public class User extends BaseEntity {

    private static final long serialVersionUID = -5108186504017368674L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    @Column(name = "email_address", nullable = false, length = 100, unique = true)
    private String emailAddress;

    @Column(name = "password",nullable = false, length = 255)
    private String password;


    public static User create(String username, String emailAddress, String password){
        User user = new User();
        user.username = username;
        user.emailAddress = emailAddress;
        user.password = password;
        return user;
    }


    public UserId getUserId(){
        return new UserId(id);
    }

}
