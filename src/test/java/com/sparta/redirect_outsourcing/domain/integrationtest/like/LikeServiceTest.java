package com.sparta.redirect_outsourcing.domain.integrationtest.like;

import com.sparta.redirect_outsourcing.domain.integrationtest.IntegrationTest;
import com.sparta.redirect_outsourcing.domain.like.dto.LikeRequestDto;
import com.sparta.redirect_outsourcing.domain.like.dto.LikeResponseRestaurantDto;
import com.sparta.redirect_outsourcing.domain.like.dto.LikeResponseReviewDto;
import com.sparta.redirect_outsourcing.domain.like.entity.ContentsTypeEnum;
import com.sparta.redirect_outsourcing.domain.like.repository.LikeQueryRepository;
import com.sparta.redirect_outsourcing.domain.like.service.LikeService;
import com.sparta.redirect_outsourcing.domain.user.dto.ProfileLikesResponseDto;
import com.sparta.redirect_outsourcing.domain.user.entity.User;
import com.sparta.redirect_outsourcing.domain.user.repository.UserRepository;
import com.sparta.redirect_outsourcing.domain.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class LikeServiceTest extends IntegrationTest {

    @Autowired
    LikeService likeService;
    @Autowired
    UserService userService;
    @Autowired
    LikeQueryRepository likeRepository;
    @Autowired
    UserRepository userRepository;

    User user;

    @BeforeEach
    void initData() {
        user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("사용자가 없음"));
    }

    @Test
    void 성공_음식점_좋아요() {
        // given
        LikeRequestDto requestDto = new LikeRequestDto();
        ReflectionTestUtils.setField(requestDto, "contentsId", 1L);
        ReflectionTestUtils.setField(requestDto, "contentsType", ContentsTypeEnum.RESTAURANT);
        // when
        likeService.addLike(user, requestDto);

        // then
        boolean isSaved = likeRepository.existsByUserAndReview(user, requestDto.getContentsId(), requestDto.getContentsType());
        assertThat(isSaved).isEqualTo(true);
    }

    @Test
    void 성공_리뷰_좋아요() {
        // given
        LikeRequestDto requestDto = new LikeRequestDto();
        ReflectionTestUtils.setField(requestDto, "contentsId", 1L);
        ReflectionTestUtils.setField(requestDto, "contentsType", ContentsTypeEnum.REVIEW);
        // when
        likeService.addLike(user, requestDto);

        // then
        boolean isSaved = likeRepository.existsByUserAndReview(user, requestDto.getContentsId(), requestDto.getContentsType());
        assertThat(isSaved).isEqualTo(true);
    }
    
    @Test
    void 성공_좋아요를_한_음식점_조회() {
        // given
        int size = 5;
        String sortBy = "createdAt";
        boolean isAsc = true;

        // when
        List<LikeResponseRestaurantDto> result1 = likeService.findLikeRestaurantsWithPage(user, 1, size, sortBy, isAsc);
        List<LikeResponseRestaurantDto> result2 = likeService.findLikeRestaurantsWithPage(user, 2, size, sortBy, isAsc);
        List<LikeResponseRestaurantDto> result3 = likeService.findLikeRestaurantsWithPage(user, 3, size, sortBy, isAsc);

        // then - testData.sql 확인
        assertThat(result1.get(0).getId()).isEqualTo(53);
        assertThat(result2.get(0).getId()).isEqualTo(48);
        assertThat(result3.get(0).getId()).isEqualTo(43);
    }

    @Test
    void 성공_좋아요를_한_리뷰_조회() {
        // given
        int size = 5;
        String sortBy = "createdAt";
        boolean isAsc = true;

        // when
        List<LikeResponseReviewDto> result1 = likeService.findLikeReviewsWithPage(user, 1, size, sortBy, isAsc);
        List<LikeResponseReviewDto> result2 = likeService.findLikeReviewsWithPage(user, 2, size, sortBy, isAsc);
        List<LikeResponseReviewDto> result3 = likeService.findLikeReviewsWithPage(user, 3, size, sortBy, isAsc);

        // then - testData.sql 확인
        assertThat(result1.get(0).getId()).isEqualTo(53);
        assertThat(result2.get(0).getId()).isEqualTo(48);
        assertThat(result3.get(0).getId()).isEqualTo(43);
    }

    @Test
    void 성공_프로필에_좋아요한_게시글수_댓글수() {
        // given
        // when
        ProfileLikesResponseDto result = userService.getProfile(user.getId());

        // then
        assertThat(result.getRestaurantLikeCount()).isEqualTo(52);
        assertThat(result.getReviewLikeCount()).isEqualTo(52);
    }
}