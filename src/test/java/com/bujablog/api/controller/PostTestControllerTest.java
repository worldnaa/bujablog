package com.bujablog.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest //작성해야 MockMvc에 값이 주입이 되어 NullPointerException이 안 뜬다
class PostTestControllerTest {

    @Autowired
    private MockMvc mockMvc; //MockMvc는 기본적으로 Content-Type을 application/json으로 보낸다

    @Test //방법1,2,3
    @DisplayName("/posts")
    void test() throws Exception {
        //localhost:8080/posts 로 POST 요청을 보낼 때 예전에는
        //Content-Type으로 "application/x-www-form-urlencoded"을 많이 썼다
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("title", "글 제목입니다.")   //param은 key-value로
                    .param("content", "글 내용입니다.") //둘다 스트링 형태이다
                )
                .andExpect(MockMvcResultMatchers.status().isOk()) //200(성공) 상태값을 기대한다
                .andExpect(MockMvcResultMatchers.content().string("Hi")) //내용은 "Hi"를 기대한다
                .andDo(MockMvcResultHandlers.print()); //HTTP 요청 정보를 출력한다
    }
}