package com.ssafy.sunin.payload.request.feed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class FeedList {
    @NotNull
    private Long userId;
    private int size;
    private int pageNum;
}