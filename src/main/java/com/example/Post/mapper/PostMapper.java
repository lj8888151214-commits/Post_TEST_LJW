package com.example.Post.mapper;

import com.example.Post.dto.Page;
import com.example.Post.dto.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    int insert(Post post);
    List<Post> selectList();
    Post selectById(Long postNo);
    int update(Post post);
    int delete(Long postNo);
    void increaseViewCount(Long postNo);

    // [문항5 추가] 페이징 처리된 목록 조회
    List<Post> selectPageList(@Param("page") Page page);

    // [문항5 추가] 전체 게시글 수 카운트
    int selectTotalCount();
}