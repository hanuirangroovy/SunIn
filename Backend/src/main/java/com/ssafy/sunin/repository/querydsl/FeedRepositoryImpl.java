package com.ssafy.sunin.repository.querydsl;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.domain.QFeedCollections;
import org.hibernate.sql.Select;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.support.QuerydslRepositorySupport;
import java.util.*;

public class FeedRepositoryImpl extends QuerydslRepositorySupport implements FeedRepositoryCustom {

    private static final QFeedCollections qfeed = QFeedCollections.feedCollections;

    public FeedRepositoryImpl(MongoOperations operations){
        super(operations);
    }

    @Override
    public List<FeedCollections> getFollowerLatesFeed(List<Long> followList) {
        return from(qfeed)
                .where(qfeed.userId.in(followList).and(qfeed.flag.eq(true)))
                .orderBy(qfeed.createdDate.desc())
                .fetch();
    }

    @Override
    public List<FeedCollections> getFollowerLikeFeed(List<Long> followList) {
        return from(qfeed)
                .where(qfeed.userId.in(followList).and(qfeed.flag.eq(true)))
                .orderBy(qfeed.likes.desc())
                .fetch();
    }

    @Override
    public List<FeedCollections> getPersonalFeed(Long userId) {
        return from(qfeed)
                .where(qfeed.userId.eq(userId).and(qfeed.flag.eq(true)))
                .orderBy(qfeed.createdDate.desc())
                .fetch();
    }

    @Override
    public Long getFeedCount(Long userId) {
        return from(qfeed)
                .where(qfeed.userId.eq(userId).and(qfeed.flag.eq(true)))
                .fetchCount();
    }
}