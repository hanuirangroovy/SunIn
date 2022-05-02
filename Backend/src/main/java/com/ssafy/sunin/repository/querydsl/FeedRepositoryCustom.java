package com.ssafy.sunin.repository.querydsl;

import com.ssafy.sunin.domain.FeedCollections;

import java.util.List;

public interface FeedRepositoryCustom {
    List<FeedCollections> getFollowerLatesFeed(List<Long> followList);

    List<FeedCollections> getFollowerLikeFeed(List<Long> followList);

    List<FeedCollections> getPersonalFeed(Long userId);

    Long getFeedCount(Long userId);
}
