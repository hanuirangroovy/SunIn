package com.ssafy.sunin.payload.request.follower;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class FollowerUser {
    @NotNull
    private Long userId;
    @NotNull
    private Long followerMember;
}