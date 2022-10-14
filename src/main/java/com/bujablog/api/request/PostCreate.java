package com.bujablog.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Setter
@Getter
public class PostCreate {

    @NotBlank(message = "타이틀을 입력해주세요.") //공백, Null 이면 에러발생
    private String title;

    @NotBlank(message = "콘텐츠를 입력해주세요.") //에러 메시지는 지정 가능
    private String content;
}
