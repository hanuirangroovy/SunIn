package com.ssafy.sunin.payload.response.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRank {
    private Long id;
    private String nickName;
    private int suninDays;

    public static UserRank userLank(Long id, String nickName, int suninDays){
        return UserRank.builder()
                .id(id)
                .nickName(nickName)
                .suninDays(suninDays)
                .build();
    }

    @Builder
    public UserRank(Long id, String nickName, int suninDays) {
        this.id = id;
        this.nickName = nickName;
        this.suninDays = suninDays;
    }
}