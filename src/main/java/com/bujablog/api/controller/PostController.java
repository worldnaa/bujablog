package com.bujablog.api.controller;

import com.bujablog.api.request.PostCreate;
import com.bujablog.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public Map<String, String> post(@RequestBody @Valid PostCreate request) {
        postService.write(request);
        Map<String, String> map = new HashMap<>();
        map.put("title", request.getTitle());
        map.put("content", request.getContent());
        return map;
    }
}
