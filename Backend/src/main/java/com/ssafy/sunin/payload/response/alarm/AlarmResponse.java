package com.ssafy.sunin.payload.response.alarm;

import com.ssafy.sunin.domain.Alarm;
import com.ssafy.sunin.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class AlarmResponse {

    private Long id;
    private String message;
    private LocalDateTime localDateTime;
    private AlarmResponseUser user;

    public static List<AlarmResponse> alarmResponseList(List<Alarm> alarms, Map<Long,User> userMap){
        return alarms.stream()
                .map(alarm -> AlarmResponse.builder()
                    .id(alarm.getId())
                    .message(alarm.getMessage())
                    .localDateTime(alarm.getLocalDateTime().plusHours(9))
                    .user(userMap.get(alarm.getFromUserId()))
                .build())
                .collect(Collectors.toList());
    }
    @Builder
    public AlarmResponse(Long id, String message, User user, LocalDateTime localDateTime) {
        this.id = id;
        this.message = message;
        this.user = AlarmResponseUser.fromUser(user);
        this.localDateTime = localDateTime;
    }

    @Data
    static class AlarmResponseUser {
        private Long userId;
        private String nickName;
        private String image;

        private AlarmResponseUser(Long userId, String nickName, String image) {
            this.userId = userId;
            this.nickName = nickName;
            this.image = image;
        }

        public static AlarmResponseUser fromUser(User user) {
            return new AlarmResponseUser(user.getUserSeq(), user.getUserNickname(), user.getProfileImageUrl());
        }

    }
}
