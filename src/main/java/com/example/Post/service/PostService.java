package com.example.Post.service;

import com.example.Post.dto.Page;
import com.example.Post.dto.Post;

import java.util.List;

public interface PostService {
    int register(Post post);
    List<Post> getList();
    Post getPost(Long postNo);
    int modify(Post post);
    int remove(Long postNo);

    // [문항5 추가]
    List<Post> getPageList(Page page);
    int getTotalCount();
}