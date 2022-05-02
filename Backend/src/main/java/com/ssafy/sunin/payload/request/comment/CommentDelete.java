package com.ssafy.sunin.payload.request.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class CommentDelete {
    @NotBlank
    private String feedId;
    @NotBlank
    private String commentId;
    @NotNull
    private Long writer;
}
