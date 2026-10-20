package com.example.Post.service;

import com.example.Post.dto.Page;
import com.example.Post.dto.Post;
import com.example.Post.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    @Override
    public int register(Post post) {
        return postMapper.insert(post);
    }

    @Override
    public List<Post> getList() {
        return postMapper.selectList();
    }

    // [문항5 추가]
    @Override
    public List<Post> getPageList(Page page) {
        return postMapper.selectPageList(page);
    }

    // [문항5 추가]
    @Override
    public int getTotalCount() {
        return postMapper.selectTotalCount();
    }

    @Override
    @Transactional
    public Post getPost(Long postNo) {
        postMapper.increaseViewCount(postNo);
        return postMapper.selectById(postNo);
    }

    @Override
    public int modify(Post post) {
        return postMapper.update(post);
    }

    @Override
    public int remove(Long postNo) {
        return postMapper.delete(postNo);
    }
}