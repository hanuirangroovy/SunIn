package com.ssafy.sunin.payload.response.user;

import com.ssafy.sunin.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSideProfile {
    private Long id;
    private String nickName;
    private String image;
    private int suninDays;

    public static UserSideProfile userSideProfile(User user){
        return UserSideProfile.builder()
                .id(user.getUserSeq())
                .nickName(user.getUserNickname())
                .image(user.getProfileImageUrl())
                .suninDays(user.getSuninDays())
                .build();
    }

    @Builder
    public UserSideProfile(Long id, String nickName, String image, int suninDays) {
        this.id = id;
        this.nickName = nickName;
        this.image = image;
        this.suninDays = suninDays;
    }
}