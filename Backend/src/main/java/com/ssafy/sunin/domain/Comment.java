package com.ssafy.sunin.domain;

import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.payload.request.comment.CommentReply;
import com.ssafy.sunin.payload.request.comment.CommentWrite;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "comments")
@ApiModel(value = "댓글 (Comment)", description = "댓글 관련 정보를 가진 Domain Class")
@NoArgsConstructor
@Getter
@ToString
public class Comment {

    private String id;

    @ApiModelProperty(value = "내용")
    private String content;

    @ApiModelProperty(value = "작성자")
    private Long writer;

    @ApiModelProperty(value = "좋아요")
    private int likes = 0;

    private Map<Long,Object> likeUser = new HashMap<>();

    @ApiModelProperty(value = "작성일자")
    @CreatedDate
    private LocalDateTime writeDate = LocalDateTime.now();

    @ApiModelProperty(value = "수정일자")
    @LastModifiedDate
    private LocalDateTime modifiedDate = LocalDateTime.now();

    @ApiModelProperty(value = "수정여부")
    private boolean modified;

    @ApiModelProperty(value = "삭제여부")
    private boolean deleted;

    @ApiModelProperty(value = "대댓글 - 루트댓글")
    private ObjectId group;

    @ApiModelProperty(value = "대댓글 - 깊이")
    private int depth;

//    private CommentUser user;

    public static Comment commentWriter(CommentWrite commentWrite, ObjectId objectId){
        return Comment.builder()
                .id(objectId.toString())
                .writer(commentWrite.getWriter())
                .content(commentWrite.getContent())
                .deleted(false)
                .modified(false)
                .writeDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .likes(0)
                .group(objectId)
                .depth(0)
                .likeUser(new HashMap<>())
                .build();
    }

    public static Comment commentReply(ObjectId objectId, CommentReply commentReply){
        return Comment.builder()
                .id(objectId.toString())
                .writer(commentReply.getWriter())
                .content(commentReply.getContent())
                .deleted(false)
                .modified(false)
                .writeDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .likes(0)
                .group(new ObjectId(commentReply.getCommentId()))
                .depth(0)
                .likeUser(new HashMap<>())
                .build();
    }

    public void setCommentModified(String content) {
        this.content = content;
        this.modified = true;
        this.modifiedDate = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    public void setCommentDeleted() {
        this.deleted = true;
    }

    public void setCommentGroup(ObjectId group) {
        this.group = group;
    }
    public void setCommentDepth(ObjectId group){
        this.group = group;
        this.depth++;
    }
    public void setCommentLikeModified(int likes, Map<Long, Object> likeUser){
        this.likes = likes;
        this.likeUser = likeUser;
    }

    @Builder
    public Comment(String id, String content, Long writer, int likes, LocalDateTime writeDate, LocalDateTime modifiedDate, boolean modified, boolean deleted, ObjectId group, int depth, User user, Map<Long,Object> likeUser) {
        this.id = id;
        this.content = content;
        this.writer = writer;
        this.likes = likes;
        this.writeDate = writeDate;
        this.modifiedDate = modifiedDate;
        this.modified = modified;
        this.deleted = deleted;
        this.group = group;
        this.depth = depth;
        this.likeUser = likeUser;
    }

}