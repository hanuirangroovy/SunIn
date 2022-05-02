package com.ssafy.sunin.payload.request.feed;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
public class FeedWrite {
    @NotNull
    private Long userId;
    private String content;
    private List<String> hashtags;
    private List<MultipartFile> files;
}