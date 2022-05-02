package com.ssafy.sunin.payload.response.feed;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.payload.response.comment.CommentDto;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@ToString
public class FeedCommentDto {
    private String id;
    private String content;
    private List<String> hashtags;
    private int likes;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<String> filePath;
    private Map<Long, Object> likeUser;
    private Map<Object, CommentDto> comments;
    //    @JsonUnwrapped
    private FeedCommentUserDto user;

    public static FeedCommentDto feedCommentDto(FeedCollections feed, User user,Map<Object,CommentDto> comments) {
        return FeedCommentDto.builder()
                .id(feed.getId().toString())
                .content(feed.getContent())
                .hashtags(feed.getHashtags())
                .likes(feed.getLikes())
                .createdDate(feed.getCreatedDate().plusHours(9))
                .modifiedDate(feed.getModifiedDate().plusHours(9))
                .likeUser(feed.getLikeUser())
                .filePath(feed.getFilePath())
                .user(user)
                .comments(comments)
                .build();
    }

    @Builder
    public FeedCommentDto(String id, String content, List<String> hashtags, int likes, LocalDateTime createdDate, LocalDateTime modifiedDate, List<String> filePath, Map<Long, Object> likeUser, Map<Object, CommentDto> comments, User user) {
        this.id = id;
        this.content = content;
        this.hashtags = hashtags;
        this.likes = likes;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.filePath = filePath;
        this.likeUser = likeUser;
        this.comments = comments;
        this.user = FeedCommentUserDto.fromUser(user);
    }

    @Data
    static class FeedCommentUserDto {
        private Long userId;
        private String nickName;
        private String image;

        private FeedCommentUserDto(Long userId, String nickName, String image) {
            this.userId = userId;
            this.nickName = nickName;
            this.image = image;
        }

        public static FeedCommentUserDto fromUser(User user) {
            return new FeedCommentUserDto(user.getUserSeq(), user.getUserNickname(), user.getProfileImageUrl());
        }

    }
}