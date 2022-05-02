package com.ssafy.sunin.domain;

import com.ssafy.sunin.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "follower")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follower{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follower_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "USER_SEQ")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_member",referencedColumnName = "USER_SEQ")
    private User followerMember;

    @CreatedDate
    @Column(name = "created_datetime")
    private LocalDateTime createDate = LocalDateTime.now();

    @Builder
    public Follower(Long id, User user, User followerMember, LocalDateTime createDate) {
        this.id = id;
        this.user = user;
        this.followerMember = followerMember;
        this.createDate = createDate;
    }
}