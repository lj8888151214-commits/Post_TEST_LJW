package com.example.Post.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Post {
    private Long postNo;
    private String title;
    private String content;
    private String writer;
    private int viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}