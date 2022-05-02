package com.ssafy.sunin.payload.request.feed;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
public class FileUpdate {
    @NotBlank
    private String id;
    private List<String> files;
    @NotNull
    private Long userId;
}