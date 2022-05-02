package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.user.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByUserId(String userId);

    User findFollowerByUserSeq(Long followerMember);

    User findByUserId(String userId);

    User findProfileByUserSeq(Long userSeq);

    List<User> findAllListByUserSeqIn(List<Long> userId);

    List<User> findAllSetByUserSeqIn(Set<Long> userId);

    List<User> findFollowerSetByUserSeqIn(Set<Long> userId);

    List<User> findTop100AllBy(Sort sort);

}