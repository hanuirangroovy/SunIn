package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.Follower;
import com.ssafy.sunin.repository.querydsl.FollowerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Follower,Long>, FollowerRepositoryCustom {
}
