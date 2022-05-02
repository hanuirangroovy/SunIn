package com.ssafy.sunin.service;

import com.ssafy.sunin.payload.request.follower.FollowerUser;
import java.util.List;

public interface FollowerService {

    Long addFollower(FollowerUser followerUser);

    Long deleteFollower(FollowerUser followerUser);

    int countFollower(Long userId);

    int countFollowing(Long followerMember);

    List<Long> getFollwingList(Long userId);
}
