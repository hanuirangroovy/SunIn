package com.ssafy.sunin.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.sunin.domain.Follower;
import com.ssafy.sunin.domain.QFollower;
import com.ssafy.sunin.domain.user.QUser;
import com.ssafy.sunin.payload.request.follower.FollowerUser;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class FollowerRepositoryImpl implements FollowerRepositoryCustom {
    private static final QFollower qFollower = QFollower.follower;
    private static final QUser qUser = QUser.user;

    private final JPAQueryFactory queryFactory;

    public FollowerRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Transactional
    @Override
    public Long deleteFollower(Follower follower) {
        return queryFactory
                .delete(qFollower)
                .where(qFollower.followerMember.eq(follower.getFollowerMember()).and(qFollower.user.eq(follower.getUser())))
                .execute();
    }

    @Override
    public List<Long> getFollowingList(Long userId) {
        return queryFactory
                .select(qUser.userSeq)
                .from(qFollower)
                .join(qFollower.followerMember, qUser)
                .where(qFollower.user.userSeq.eq(userId))
                .distinct()
                .fetch();
    }

    @Override
    public int getFollowingCount(Long userId) {
        return queryFactory
                .select(qFollower.count())
                .from(qFollower)
                .where(qFollower.user.userSeq.eq(userId))
                .fetch().get(0).intValue();
    }

    @Override
    public int getFollowerCount(Long followerMember) {
        return queryFactory
                .select(qFollower.count())
                .from(qFollower)
                .where(qFollower.followerMember.userSeq.eq(followerMember))
                .fetch().get(0).intValue();
    }

    @Override
    public Follower getUser(FollowerUser followerUser) {
        return queryFactory
                .selectFrom(qFollower)
                .leftJoin(qFollower.user, qUser)
                .fetchJoin()
                .leftJoin(qFollower.followerMember, qUser)
                .where(qFollower.user.userSeq.eq(followerUser.getUserId())
                        .and(qFollower.followerMember.userSeq.eq(followerUser.getFollowerMember())))
                .fetchOne();
    }
}
