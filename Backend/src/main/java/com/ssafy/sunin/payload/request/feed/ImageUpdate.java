package com.ssafy.sunin.payload.request.feed;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class ImageUpdate {
    @NotNull
    private Long id;
    private MultipartFile image;
}