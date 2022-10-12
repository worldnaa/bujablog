package com.bujablog.api.controller;

import com.bujablog.api.request.PostTestCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j //log 사용
@RestController
public class PostTestController {
    //Http Method : GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT

    @PostMapping("/posts") //요청받은 값을 서버에서 가져오는 방법1
    public String post(@RequestParam String title, @RequestParam String content) {
        log.info("title={}, content={}", title, content);
        return "Hi";
    }

//    @PostMapping("/posts") //요청받은 값을 서버에서 가져오는 방법2
    public String post(@RequestParam Map<String, String> params) {
        log.info("params={}", params.toString());
        String title = params.get("title");
        return "Hi";
    }

//    @PostMapping("/posts") //요청받은 값을 서버에서 가져오는 방법3
    public String post(PostTestCreate params) {
        log.info("params={}", params.toString());
        return "Hi";
    }


}
