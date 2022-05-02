package com.ssafy.sunin.payload.response.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserProfile {
    private Long id;
    private int follwerCount;
    private int follwingCount;
    private int feedCount;

    public static UserProfile userProfile(Long id, int follwerCount, int follwingCount, int feedCount){
        return UserProfile.builder()
                .id(id)
                .follwerCount(follwerCount)
                .feedCount(feedCount)
                .follwingCount(follwingCount)
                .build();
    }

    @Builder
    public UserProfile(Long id, int follwerCount, int follwingCount, int feedCount) {
        this.id = id;
        this.follwerCount = follwerCount;
        this.follwingCount = follwingCount;
        this.feedCount = feedCount;
    }

}
