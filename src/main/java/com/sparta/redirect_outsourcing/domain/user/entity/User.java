package com.sparta.redirect_outsourcing.domain.user.entity;

import com.sparta.redirect_outsourcing.common.TimeStampEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="users")
public class User extends TimeStampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String username;

    @NotNull
    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    private String introduce;

    @Column
    private String pictureUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column
    private UserStatusEnum userStatus;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column
    private UserRoleEnum userRole;

    @Column
    private String refreshToken;

    @Column
    private Long kakaoId;

    @ElementCollection
    @Column
    private List<String> previousPasswords = new ArrayList<>();

    public User(Long kakaoId, String username, String pictureUrl, String password) {
        this.kakaoId = kakaoId;
        this.pictureUrl = pictureUrl;
        this.username = username;
        this.password = password;
        this.userStatus = UserStatusEnum.STATUS_NORMAL;
        this.userRole = UserRoleEnum.ROLE_USER;
    }
}