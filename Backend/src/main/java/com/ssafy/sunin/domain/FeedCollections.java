package com.ssafy.sunin.domain;

import com.ssafy.sunin.payload.request.feed.FeedUpdate;
import com.ssafy.sunin.payload.request.feed.FeedWrite;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document("feed")
@ApiModel(value = "피드", description = "피드 콜렉션")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedCollections{

    @Id
    private ObjectId id;

    private Long userId;

    private String content;

    private List<String> hashtags = new ArrayList<>();

    private int likes;

    @NotNull
    private Map<Long,Object> likeUser = new HashMap<>();

    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedDate
    private LocalDateTime modifiedDate = LocalDateTime.now();

    private List<String> filePath = new ArrayList<>();

    private boolean flag = true;

    private Map<Object,Comment> comments = new HashMap<>();

    public void setFeedModified(FeedUpdate feedUpdate){
        this.content = feedUpdate.getContent();;
        this.hashtags = feedUpdate.getHashtags();
        this.modifiedDate = LocalDateTime.now();
    };

    public void setFeedDelete(){
        this.flag = false;
        this.modifiedDate = LocalDateTime.now();
    }

    public void setLikeModified(int likes, Map<Long, Object> likeUser){
        this.likes = likes;
        this.likeUser = likeUser;
    }

    public void setFileModified(List<String> filePath){
        this.filePath = filePath;
    }

    public void setCommentWrite(Map<Object,Comment> comments){
        this.comments = comments;
    }

    public void setCommentLikeUsers(Map<Object,Comment> comments){
        this.comments = comments;
    }

    public static FeedCollections setFeedCollection(FeedWrite feedWrite, List<String> fileList){
        return  FeedCollections.builder()
                .userId(feedWrite.getUserId())
                .content(feedWrite.getContent())
                .hashtags(feedWrite.getHashtags())
                .likes(0)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .flag(true)
                .likeUser(new HashMap<>())
                .filePath(fileList)
                .comments(new HashMap<>())
                .build();
    }

    @Builder
    public FeedCollections(ObjectId id, Long userId, String content, List<String> hashtags, int likes, Map<Long,Object> likeUser, LocalDateTime createdDate, LocalDateTime modifiedDate, List<String> filePath, boolean flag, Map<Object,Comment> comments) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.hashtags = hashtags;
        this.likes = likes;
        this.likeUser = likeUser;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.filePath = filePath;
        this.flag = flag;
        this.comments = comments;
    }
}