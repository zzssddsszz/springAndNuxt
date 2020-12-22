package kr.co.iamdesigner.domain.model.user;

import kr.co.iamdesigner.domain.common.model.abstractBaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor @NoArgsConstructor
@ToString(exclude = {"password"})
@Builder
@Table(name = "user")
public class User extends abstractBaseEntity {

    private static final long serialVersionUID = -5108186504017368674L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    @Column(name = "email_address", nullable = false, length = 100, unique = true)
    private String emailAddress;

    @Column(name = "password",nullable = false, length = 30)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    @Builder.Default private Date createdDate = new Date();

}
