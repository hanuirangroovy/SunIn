package com.ssafy.sunin.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.sunin.domain.Follower;
import com.ssafy.sunin.oauth.entity.ProviderType;
import com.ssafy.sunin.oauth.entity.RoleType;
import com.ssafy.sunin.payload.request.user.UserUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User {
    @Id
    @Column(name = "USER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    @Column(name = "USER_ID", length = 64, unique = true)
    @NotNull
    @Size(max = 64)
    private String userId;

    @Column(name = "USERNAME", length = 100)
    @NotNull
    @Size(max = 100)
    private String username;

    @JsonIgnore
    @Column(name = "PASSWORD", length = 128)
    @NotNull
    @Size(max = 128)
    private String password;

    private String userNickname;

    @Column(name = "EMAIL", length = 512, unique = true)
    @NotNull
    @Size(max = 512)
    private String email;

    @Column(name = "EMAIL_VERIFIED_YN", length = 1)
    @NotNull
    @Size(min = 1, max = 1)
    private String emailVerifiedYn;

    @Column(name = "PROFILE_IMAGE_URL", length = 512)
    @NotNull
    @Size(max = 512)
    private String profileImageUrl;

    @Column(name = "PROVIDER_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProviderType providerType;

    @Column(name = "ROLE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;

    @Column(name = "CREATED_AT")
    @NotNull
    private LocalDateTime createdAt;

    @Column(name = "MODIFIED_AT")
    @NotNull
    private LocalDateTime modifiedAt;

    private String introduction;

    private String address;

    private String phoneNumber;

    private int suninDays = 0;

    public void setSuninDayIncrease(){
        this.suninDays++;
    }

    public User(
            @NotNull @Size(max = 64) String userId,
            @NotNull @Size(max = 100) String username,
            @NotNull @Size(max = 512) String email,
            @NotNull @Size(max = 1) String emailVerifiedYn,
            @NotNull @Size(max = 512) String profileImageUrl,
            @NotNull ProviderType providerType,
            @NotNull RoleType roleType,
            @NotNull LocalDateTime createdAt,
            @NotNull LocalDateTime modifiedAt,
            String introduction,
            String address,
            String phoneNumber
    ) {
        this.userId = userId;
        this.username = username;
        this.password = "NO_PASS";
        this.email = email != null ? email : "NO_EMAIL";
        this.emailVerifiedYn = emailVerifiedYn;
        this.profileImageUrl = profileImageUrl != null ? profileImageUrl : "";
        this.providerType = providerType;
        this.roleType = roleType;
        this.createdAt = createdAt;
        this.userNickname = username;
        this.modifiedAt = modifiedAt;
        this.introduction = introduction;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


    @OneToMany(mappedBy = "user")
    private List<Follower> follower = new ArrayList<>();

    public void setUserProfileModifed(UserUpdateRequest userUpdateRequest, String image){
        this.profileImageUrl = image;
        this.userNickname = userUpdateRequest.getNickName();
        this.introduction = userUpdateRequest.getIntroduction();
        this.phoneNumber = userUpdateRequest.getPhoneNumber();
        this.address = userUpdateRequest.getAddress();
    }

    public void setUserProfileImageModified(String profileImageUrl){
        this.profileImageUrl = profileImageUrl;
    }

    public void setUserNameModified(String userName){
        this.username = userName;
    }

    public void setSunindDaysDecrease(User user){
        this.suninDays = --user.suninDays;
    }
}