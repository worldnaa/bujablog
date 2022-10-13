package com.bujablog.api.controller;

import com.bujablog.api.request.PostTestCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j //log 사용
@RestController
public class PostTestController {

//    @PostMapping("/post1") //요청받은 파라미터 값을 서버에서 가져오는 방법1
    public String post1(@RequestParam String title, @RequestParam String content) {
        log.info("title={}, content={}", title, content);
        return "Hi";
    }

//    @PostMapping("/post1") //요청받은 파라미터 값을 서버에서 가져오는 방법2
    public String post1(@RequestParam Map<String, String> params) {
        log.info("params={}", params.toString());
        String title = params.get("title");
        return "Hi";
    }

    @PostMapping("/post1") //요청받은 파라미터 값을 서버에서 가져오는 방법3
    public String post1(PostTestCreate params) {
        log.info("params={}", params.toString());
        return "Hi";
    }

    @PostMapping("/post2") //요청받은 json 값은 body에 담겨있으므로 @RequestBody 사용
    public String post2(@RequestBody PostTestCreate params) {
        log.info("params={}", params.toString());
        return "Hi";
    }

    @PostMapping("post3") //@Valid : 데이터 검증 / BindingResult : 오류의 내용이 담겨온다
    public Map<String, String> post3(@RequestBody @Valid PostTestCreate params, BindingResult result){
        if (result.hasErrors()) {                                      //에러가 있으면
            List<FieldError> fieldErrors = result.getFieldErrors();    //에러를 다 가져와서 List에 담는다
            FieldError firstFieldError = fieldErrors.get(0);           //에러들 중 첫번째 에러를 가져온다
            String fieldName = firstFieldError.getField();             //title 자체를 가져와 저장
            String errorMessage = firstFieldError.getDefaultMessage(); //에러 메시지를 가져와 저장

            Map<String, String> error = new HashMap<>();
            error.put(fieldName, errorMessage);
            return error;
        }
        Map<String, String> map = new HashMap<>();
        map.put("title", params.getTitle());
        map.put("content", params.getContent());
        return map; //java 11이상(?) 에서는 map.of() 지원
    }




}
