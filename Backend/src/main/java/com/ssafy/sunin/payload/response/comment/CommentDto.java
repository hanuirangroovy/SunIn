package com.ssafy.sunin.payload.response.comment;

import com.ssafy.sunin.domain.Comment;
import com.ssafy.sunin.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class CommentDto {
    private String id;
    @ApiModelProperty(value = "내용")
    private String content;

    @ApiModelProperty(value = "작성자")
    private Long writer;

    @ApiModelProperty(value = "좋아요")
    private int likes = 0;

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

    private CommentDtoUser user;

    public static List<CommentDto> mapCommentDto(List<Comment> comments, Map<Long, User> userMap){
        return comments.stream()
                .map(comment -> CommentDto.builder()
                        .id(comment.getId())
                        .writer(comment.getWriter())
                        .content(comment.getContent())
                        .likes(comment.getLikes())
                        .writeDate(comment.getWriteDate().plusHours(9))
                        .modifiedDate(comment.getModifiedDate().plusHours(9))
                        .modified(comment.isModified())
                        .deleted(comment.isDeleted())
                        .group(comment.getGroup())
                        .user(userMap.get(comment.getWriter()))
                        .depth(comment.getDepth())
                        .build())
                .collect(Collectors.toList());
    }

    @Builder
    public CommentDto(String id ,String content, Long writer, int likes, LocalDateTime writeDate, LocalDateTime modifiedDate, boolean modified, boolean deleted, ObjectId group, int depth, User user) {
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
        this.user = CommentDtoUser.fromUser(user);
    }

    @Data
    static class CommentDtoUser {
        private Long userId;
        private String nickName;
        private String image;

        private CommentDtoUser(Long userId, String nickName, String image) {
            this.userId = userId;
            this.nickName = nickName;
            this.image = image;
        }

        public static CommentDtoUser fromUser(User user) {
            return new CommentDtoUser(user.getUserSeq(), user.getUserNickname(), user.getProfileImageUrl());
        }

    }
}
