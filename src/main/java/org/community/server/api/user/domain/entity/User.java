package org.community.server.api.user.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.community.server.api.user.exception.UserCrudErrorCode;
import org.community.server.exception.common.BizException;
import org.hibernate.annotations.Comment;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class User {

    @Id
    private Long id;

    @Column(name = "user_id", length = 60, nullable = false, unique = true)
    @Comment("user id")
    private String userId;

    @Column(name = "name", length = 60, nullable = false)
    @Comment("user name")
    private String name;

    @Column(name = "password", length = 200, nullable = false)
    @Comment("user password")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserRole> userRoleList = new ArrayList<>();

    @Builder
    public User(Long id, String userId, String name, String password, List<UserRole> userRoleList) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.userRoleList = userRoleList;
    }

    public void login(PasswordEncoder passwordEncoder, String credentials) {
        if (!passwordEncoder.matches(credentials, this.password)) {
            throw new BizException(UserCrudErrorCode.INCORRECT_USERID_OR_PASSWORD);
        }
    }

}
