package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.repository.querydsl.FeedRepositoryCustom;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.List;
import java.util.Optional;

public interface FeedRepository extends MongoRepository<FeedCollections, ObjectId>,
                                        QuerydslPredicateExecutor<FeedCollections>,
                        FeedRepositoryCustom {

    FeedCollections findByIdAndFlagTrue(ObjectId id);

    FeedCollections findByIdAndUserIdAndFlagTrue(ObjectId id, Long userId);

    List<FeedCollections> findAllByFlagTrue(Pageable pageable);

    FeedCollections findFeedIdByIdAndFlagTrue(ObjectId id);

    FeedCollections findFeedSortIdByIdAndFlagTrue(ObjectId id);

    List<FeedCollections> findByContentContainsAndFlagTrue(String content,Pageable pageable);

    List<FeedCollections> findAllByFlagTrue();

}