package com.ssafy.sunin.payload.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UserUpdateRequest {
    @NotNull
    private Long userId;
    private MultipartFile image;
    private String nickName;
    private String introduction;
    private String phoneNumber;
    private String address;
}
