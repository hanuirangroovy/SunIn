package com.ssafy.sunin.controller;

import com.ssafy.sunin.payload.request.follower.FollowerUser;
import com.ssafy.sunin.service.FollowerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/follower")
@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice(annotations = RestController.class)
public class FollowerController {

    private final FollowerService followerService;

    @ApiOperation(value = "팔로워 추가", notes = "userId: 현재 로그인중인 유저 id, follower_member: 팔로워할 멤버 id")
    @PostMapping
    public ResponseEntity<String> addFollower(@RequestBody @Valid FollowerUser followerUser){
        log.info("addFollower");
        if(ObjectUtils.isEmpty(followerUser)){
            return ResponseEntity.notFound().build();
        }

        Long result = followerService.addFollower(followerUser);
        System.out.println(result);
        if(result == 1) return new ResponseEntity<>("등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        else return new ResponseEntity<>("등록 성공", HttpStatus.CREATED);
    }

    @ApiOperation(value = "팔로워 삭제", notes = "userId: 현재 로그인중인 유저 id, follower_member: 팔로워할 멤버 id")
    @DeleteMapping
    public ResponseEntity<String> deleteFollower(@Valid FollowerUser followerUser){
        log.info("deleteFollower");
        if(ObjectUtils.isEmpty(followerUser)){
            return ResponseEntity.notFound().build();
        }

        Long result = followerService.deleteFollower(followerUser);
        System.out.println(result);
        if(result == null) return new ResponseEntity<>("등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        else return new ResponseEntity<>("등록 성공", HttpStatus.CREATED);
    }

    @ApiOperation(value = "팔로워 수 조회", notes = "userId: 현재 로그인중인 유저 id, 팔로워 수 조회")
    @GetMapping("/follower/{userId}")
    public ResponseEntity<Integer> getFollowerCount(@PathVariable("userId") Long userId){
        log.info("getFollwerCount");
        return ResponseEntity.ok(followerService.countFollower(userId));
    }

    @ApiOperation(value = "팔로잉 수 조회", notes = "userId: 현재 로그인중인 유저 id, 팔로잉 수 조회")
    @GetMapping("/following/{userId}")
    public ResponseEntity<Integer> getFollowingCount(@PathVariable("userId") Long userId){
        log.info("getFollowingCount");
        return ResponseEntity.ok(followerService.countFollowing(userId));
    }

    @ApiOperation(value = "내가 팔로한 한 사람들의 리스트 - 팔로잉 리스트")
    @GetMapping("/followingList/{userId}")
    public ResponseEntity<List<Long>> getFollowingList(@PathVariable("userId") Long userId){
        log.info("getFollowingList");
        return ResponseEntity.ok(followerService.getFollwingList(userId));
    }
}