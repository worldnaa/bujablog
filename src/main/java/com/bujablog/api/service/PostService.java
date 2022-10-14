package com.bujablog.api.service;

import com.bujablog.api.domain.Post;
import com.bujablog.api.repository.PostRepository;
import com.bujablog.api.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate) {
        Post post = new Post(postCreate.getTitle(), postCreate.getContent());
        postRepository.save(post);
    }
}
