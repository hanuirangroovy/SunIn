package com.ssafy.sunin.payload.response.feed;

import com.ssafy.sunin.domain.FeedCollections;
import com.ssafy.sunin.domain.user.User;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@ToString
public class FeedDto {
    private String id;
    private String content;
    private List<String> hashtags;
    private int likes;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<String> filePath;
    private Map<Long, Object> likeUser;
    private int commentCount;
//    @JsonUnwrapped
    private FeedUserDto user;

    public static List<FeedDto> mapFeedDto(List<FeedCollections> feedCollection, Map<Long,User> userMap){
        return feedCollection.stream()
                .map(feedCollections -> FeedDto.builder()
                        .id(feedCollections.getId().toString())
                        .content(feedCollections.getContent())
                        .hashtags(feedCollections.getHashtags())
                        .likes(feedCollections.getLikes())
                        .createdDate(feedCollections.getCreatedDate().plusHours(9))
                        .modifiedDate(feedCollections.getModifiedDate().plusHours(9))
                        .likeUser(feedCollections.getLikeUser())
                        .filePath(feedCollections.getFilePath())
                        .commentCount(feedCollections.getComments().size())
                        .user(userMap.get(feedCollections.getUserId()))
                        .build())
                .collect(Collectors.toList());
    }

    public static List<FeedDto> personFeedDto(List<FeedCollections> feedCollection, User user) {
        return feedCollection.stream()
                .map(feedCollections -> FeedDto.builder()
                        .id(feedCollections.getId().toString())
                        .content(feedCollections.getContent())
                        .hashtags(feedCollections.getHashtags())
                        .likes(feedCollections.getLikes())
                        .createdDate(feedCollections.getCreatedDate().plusHours(9))
                        .modifiedDate(feedCollections.getModifiedDate().plusHours(9))
                        .likeUser(feedCollections.getLikeUser())
                        .filePath(feedCollections.getFilePath())
                        .commentCount(feedCollections.getComments().size())
                        .user(user)
                        .build())
                .collect(Collectors.toList());
    }

    @Builder
    public FeedDto(String id, String content, List<String> hashtags, int likes, LocalDateTime createdDate, LocalDateTime modifiedDate, List<String> filePath, Map<Long, Object> likeUser, int commentCount, User user) {
        this.id = id;
        this.content = content;
        this.hashtags = hashtags;
        this.likes = likes;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.filePath = filePath;
        this.likeUser = likeUser;
        this.commentCount = commentCount;
        this.user = FeedUserDto.fromUser(user);
    }

    @Data
    static class FeedUserDto {
        private Long userId;
        private String nickName;
        private String image;

        private FeedUserDto(Long userId, String nickName, String image) {
            this.userId = userId;
            this.nickName = nickName;
            this.image = image;
        }

        public static FeedUserDto fromUser(User user) {
            return new FeedUserDto(user.getUserSeq(), user.getUserNickname(), user.getProfileImageUrl());
        }

    }

}