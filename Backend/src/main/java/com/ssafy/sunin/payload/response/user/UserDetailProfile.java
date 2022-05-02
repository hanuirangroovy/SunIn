package com.ssafy.sunin.payload.response.user;

import com.ssafy.sunin.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDetailProfile {
    private Long id;
    private String nickName;
    private String image;
    private String introduction;
    private int follwerCount;
    private int follwingCount;
    private int suninDays;
    private int feedCount;

    public static UserDetailProfile userProfile(User user) {
        return UserDetailProfile.builder()
                .id(user.getUserSeq())
                .nickName(user.getUserNickname())
                .image(user.getProfileImageUrl())
                .suninDays(user.getSuninDays())
                .build();
       }

    public static UserDetailProfile userProfileDto(User user, UserProfile userProfile){
        return UserDetailProfile.builder()
                .id(user.getUserSeq())
                .nickName(user.getUserNickname())
                .image(user.getProfileImageUrl())
                .introduction(user.getIntroduction())
                .follwerCount(userProfile.getFollwerCount())
                .follwingCount(userProfile.getFollwingCount())
                .suninDays(user.getSuninDays())
                .feedCount(userProfile.getFeedCount())
                .build();
    }

   @Builder
    public UserDetailProfile(Long id, String nickName, String image, String introduction, int follwerCount, int follwingCount, int suninDays, int feedCount) {
        this.id = id;
        this.nickName = nickName;
        this.image = image;
        this.introduction = introduction;
        this.follwerCount = follwerCount;
        this.follwingCount = follwingCount;
        this.suninDays = suninDays;
        this.feedCount = feedCount;
    }
}
