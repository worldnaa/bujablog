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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest //작성해야 MockMvc에 값이 주입이 되어 NullPointerException이 안 뜬다
class PostTestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/post1 요청시 Hi를 출력_x-www-form-urlencoded")
    void test1() throws Exception {
        //localhost:8080/post1 로 POST 요청을 보낸다 (Content-Type: "application/x-www-form-urlencoded")
        mockMvc.perform(MockMvcRequestBuilders.post("/post1")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("title", "글 제목입니다.")   //param은 key-value로 둘 다 스트링 형태며,
                    .param("content", "글 내용입니다.") //헤더에 파라미터로 붙어서 들어온다
                )
                .andExpect(MockMvcResultMatchers.status().isOk()) //200(성공) 상태값을 기대한다
                .andExpect(MockMvcResultMatchers.content().string("Hi")) //내용은 "Hi"를 기대한다
                .andDo(MockMvcResultHandlers.print()); //HTTP 요청 정보를 출력한다
    }

    @Test
    @DisplayName("/post2 요청시 Hi를 출력_json")
    void test2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/post2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"제목입니다.\", \"content\": \"내용입니다.\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hi"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("/post3 요청시 title과 content는 필수")
    void test3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/post3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": null, \"content\": \"내용입니다.\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                //jsonPath에 대해 공부하기 (아래는 title의 값이 아래 value 값일 것으로 기대한다)
                .andExpect(jsonPath("$.title").value("타이틀을 입력해주세요."))
                .andDo(MockMvcResultHandlers.print());
    }
}