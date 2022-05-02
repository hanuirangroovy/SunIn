package com.ssafy.sunin.payload.response.comment;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentProfile {
    private Long id;
    private String nickName;
    private String image;

    @Builder
    public CommentProfile(Long id, String nickName, String image) {
        this.id = id;
        this.nickName = nickName;
        this.image = image;
    }
}
