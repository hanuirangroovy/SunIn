package com.ssafy.sunin.payload.response.user;

import com.ssafy.sunin.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateResponse {
    private Long id;
    private String image;
    private String nickName;
    private String introduction;
    private String phoneNumber;
    private String address;

    public static UserUpdateResponse userUpdate(User user) {
        return UserUpdateResponse.builder()
                .id(user.getUserSeq())
                .image(user.getProfileImageUrl())
                .nickName(user.getUserNickname())
                .introduction(user.getIntroduction())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .build();
    }

    @Builder
    public UserUpdateResponse(Long id, String image, String nickName, String introduction, String phoneNumber, String address) {
        this.id = id;
        this.image = image;
        this.nickName = nickName;
        this.introduction = introduction;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
