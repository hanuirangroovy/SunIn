package com.ssafy.sunin.payload.request.feed;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
public class FeedUpdate {
    @NotBlank
    private String id;
    private String content;
    @NotNull
    private Long userId;
    private List<String> hashtags;
    private List<MultipartFile> files;
}