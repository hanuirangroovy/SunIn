package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.Follower;
import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.payload.request.follower.FollowerUser;
import com.ssafy.sunin.repository.FollowerRepository;
import com.ssafy.sunin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowerServiceImpl implements FollowerService{

    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;

    @Override
    public Long addFollower(FollowerUser followerUser) {
        if(followerUser.getFollowerMember().equals(followerUser.getUserId())) return 1L;
        // 보내는 유저 체크
        Optional<User> user = userRepository.findById(followerUser.getUserId());
        Long count = user.stream().count();
        User users = user.get();
        // 팔로워 받는 유저 체크
        User followerMember = userRepository.findFollowerByUserSeq(followerUser.getFollowerMember());
        // 팔로워
        if(followerRepository.getUser(followerUser) == null){
            Follower follower = Follower.builder()
                    .user(users)
                    .followerMember(followerMember)
                    .createDate(followerMember.getCreatedAt())
                    .build();
            count++;
            followerRepository.save(follower);
        }

        return count;
    }

    @Override
    public Long deleteFollower(FollowerUser followerUser) {
        Follower follower = followerRepository.getUser(followerUser);
        return followerRepository.deleteFollower(follower);
    }

    @Override
    public int countFollowing(Long userId) {
        return followerRepository.getFollowingCount(userId);
    }

    @Override
    public int countFollower(Long followerMember) {
        return followerRepository.getFollowerCount(followerMember);
    }

    @Override
    public List<Long> getFollwingList(Long userId) {
        return followerRepository.getFollowingList(userId);
    }
}
