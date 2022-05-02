package com.ssafy.sunin.repository.querydsl;

import com.ssafy.sunin.domain.Follower;
import com.ssafy.sunin.payload.request.follower.FollowerUser;
import java.util.List;

public interface FollowerRepositoryCustom {

    Long deleteFollower(Follower follower);

    List<Long> getFollowingList(Long userId);

    int getFollowingCount(Long userId);

    int getFollowerCount(Long followerMember);

    Follower getUser(FollowerUser followerUser);
}
