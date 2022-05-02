package com.ssafy.sunin.payload.request.feed;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class FeedLike {
    @NotBlank
    private String id;
    @NotNull
    private Long userId;
}
