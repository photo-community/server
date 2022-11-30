package org.community.server.api.user.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.community.server.api.user.domain.enums.UserRoleType;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_role")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class UserRole {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Comment("authorization")
    private UserRoleType role;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.DETACH)
    private User user;

    @Builder
    public UserRole(Long id, UserRoleType role, User user) {
        this.id = id;
        this.role = role;
        this.user = user;
    }
}
