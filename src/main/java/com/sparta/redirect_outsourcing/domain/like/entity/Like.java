package com.sparta.redirect_outsourcing.domain.like.entity;

import com.sparta.redirect_outsourcing.common.TimeStampEntity;
import com.sparta.redirect_outsourcing.domain.like.dto.LikeRequestDto;
import com.sparta.redirect_outsourcing.domain.review.entity.Review;
import com.sparta.redirect_outsourcing.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "likes")
public class Like extends TimeStampEntity {

    /**
     * 컬럼
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "contents_id", nullable = false)
    private Long contentsId;

    /**
     * 연관관계
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable = false)
    private User user;
    @Enumerated(EnumType.STRING)
    @Column(name = "content_type", nullable = false)
    private ContentsTypeEnum contentsType;

    /**
     * 생성자
     */
    public Like(User user, Long contentsId, ContentsTypeEnum contentsType) {
        this.user = user;
        this.contentsId = contentsId;
        this.contentsType = contentsType;
    }

    public static Like of(User user, LikeRequestDto requestDto) {
        return new Like(user, requestDto.getContentsId(), requestDto.getContentsType());
    }
    /**
     * 서비스 메소드 - 외부에서 엔티티를 수정할 메소드를 정의
     */
}