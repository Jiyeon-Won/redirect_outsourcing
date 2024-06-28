package com.sparta.redirect_outsourcing.domain.like.dto;

import com.sparta.redirect_outsourcing.domain.like.entity.ContentsTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class LikeRequestDto {
    @NotNull(message = "좋아요를 등록할 ID를 입력해 주세요.")
    private Long contentsId;
    @NotNull(message = "좋아요를 등록할 타입을 입력해 주세요.")
    private ContentsTypeEnum contentsType;
}