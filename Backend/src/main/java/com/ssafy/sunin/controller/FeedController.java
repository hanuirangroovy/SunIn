package com.ssafy.sunin.controller;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.payload.response.feed.FeedSearch;
import com.ssafy.sunin.payload.response.user.UserDetailProfile;
import com.ssafy.sunin.payload.request.feed.*;
import com.ssafy.sunin.payload.response.feed.FeedCommentDto;
import com.ssafy.sunin.payload.response.feed.FeedDto;
import com.ssafy.sunin.service.FeedServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RestControllerAdvice(annotations = RestController.class)
@RequestMapping("/feed")
@Slf4j
public class FeedController {

    private final FeedServiceImpl feedService;

    @ApiOperation(value = "Feed 작성", notes = "다중 파일 업로드 가능")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> writeImageFeed(@RequestBody @Valid FeedWrite feedWrite){
        log.info("writerImageFeed");

        if(ObjectUtils.isEmpty(feedWrite)){
            return ResponseEntity.notFound().build();
        }

        FeedCollections result = feedService.writeImageFeed(feedWrite);
        if(result == null) return new ResponseEntity<>("등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        else return new ResponseEntity<>("등록 성공", HttpStatus.CREATED);
    }

    @ApiOperation(value = "피드 상세 페이지")
    @GetMapping("/detail/{id}")
    public ResponseEntity<FeedCommentDto> getDetailFeed(@PathVariable("id") String id) {
        log.info("getDetailFeed");
        return ResponseEntity.ok(feedService.getDetailFeed(id));
    }

    @ApiOperation(value = "피드 수정")
    @PutMapping
    public ResponseEntity<String> updateFeed(@RequestBody @Valid FeedUpdate feedUpdate){
        log.info("updateFeed");
        if(ObjectUtils.isEmpty(feedUpdate)){
            return ResponseEntity.notFound().build();
        }

        FeedCollections result = feedService.updateFeed(feedUpdate);
        if(result == null) return new ResponseEntity<>("등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        else return new ResponseEntity<>("등록 성공", HttpStatus.CREATED);

    }

    @ApiOperation(value = "피드 삭제")
    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<String> deleteFeed(@PathVariable("id") String id, @PathVariable Long userId) {
        log.info("deleteFeed");

        FeedCollections result = feedService.deleteFeed(id,userId);
        if(result == null) return new ResponseEntity<>("등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        else return new ResponseEntity<>("등록 성공", HttpStatus.CREATED);
    }

    @ApiOperation(value = "나의 팔로워 최신순 피드 조회", notes = "나의 userId 값 전달")
    @GetMapping("/followerLatest/{userId}")
    public ResponseEntity<List<FeedDto>> getFollowerLatestFeed(@PathVariable("userId") Long userId){
        log.info("getFollowerFeed");
        return ResponseEntity.ok(feedService.getFollowerLatestFeed(userId));
    }

    @ApiOperation(value = "나의 팔로워 좋아요순 피드 조회 피드 ", notes = "나의 userId 값 전달")
    @GetMapping("/followerLike/{userId}")
    public ResponseEntity<List<FeedDto>> getFollowerLikeFeed(@PathVariable("userId") Long userId){
        log.info("getFollowerLikeFeed");
        return ResponseEntity.ok(feedService.getFollowerLikeFeed(userId));
    }

    @ApiOperation(value = "나의 피드 최신순 프로필용")
    @GetMapping("/person/{userId}")
    public ResponseEntity<List<FeedDto>> getPersonalFeed(@PathVariable("userId") Long userId){
        log.info("getPersonalFeed");
        return ResponseEntity.ok(feedService.getPersonalFeed(userId));
    }


    @ApiOperation(value = "전체 최신순 피드 조회")
    @GetMapping("/latest")
    public ResponseEntity<List<FeedDto>> getLatestFeed(@PageableDefault(sort="createdDate",
                                                            direction = Sort.Direction.DESC) Pageable pageable){
        log.info("getLatestFeed");
        return ResponseEntity.ok(feedService.getLatestFeed(pageable));
    }

    @ApiOperation(value = "전체 좋아요순 피드 조회")
    @GetMapping("/like")
    public ResponseEntity<List<FeedDto>> getLikesFeed(@PageableDefault(sort="likes",
                                                            direction = Sort.Direction.DESC) Pageable pageable){
        log.info("getLikesFeed");
        return ResponseEntity.ok(feedService.getLikeFeed(pageable));
    }

    @ApiOperation(value = "좋아요 등록 취소")
    @PutMapping("/addLike")
    public ResponseEntity<String> likeFeed(@RequestBody @Valid FeedLike feedLike){
        log.info("likeFeed");
        if(ObjectUtils.isEmpty(feedLike)){
            return ResponseEntity.notFound().build();
        }

        FeedCollections result = feedService.likeFeed(feedLike);
        if(result == null) return new ResponseEntity<>("등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        else return new ResponseEntity<>("등록 성공", HttpStatus.CREATED);
    }

    @ApiOperation(value = "게시글의 좋아요를 누른 유저들의 프로필, 피드 id")
    @GetMapping("/likeUser/{id}")
    public ResponseEntity<List<UserDetailProfile>> getLikeUserList(@PathVariable("id") String id){
        log.info("likeUserList");

        return ResponseEntity.ok(feedService.getLikeUserList(id));
    }

    @ApiOperation(value = "내용 및 해시태그 검색 창", notes = "해시태그 검색은 #내용, 내용검색은 내용만 보내면 됨")
    @GetMapping("/search")
    public ResponseEntity<FeedSearch> getSearchList(@PageableDefault(size = 30,sort = "createdDate",
                                                            direction = Sort.Direction.DESC) Pageable pageable, @RequestParam("content") String content){
        log.info("getSearchList");

        return ResponseEntity.ok(feedService.getSearchList(pageable,content));
    }
}