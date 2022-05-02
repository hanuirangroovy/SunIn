package com.ssafy.sunin.payload.request.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class CommentWrite {
    @NotBlank
    private String feedId;
    @NotNull
    private Long writer;
    private String content;
}
