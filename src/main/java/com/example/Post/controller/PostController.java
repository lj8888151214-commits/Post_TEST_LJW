package com.example.Post.controller;

import com.example.Post.dto.Page;
import com.example.Post.dto.Post;
import com.example.Post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // [문항5 수정] 페이징 파라미터 수신 및 페이징 목록 바인딩
    @GetMapping("/list")
    public String list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                       @RequestParam(value = "rowsPerPage", defaultValue = "10") int rowsPerPage,
                       Model model) {
        int totalCount = postService.getTotalCount();
        Page page = new Page(pageNum, rowsPerPage, totalCount);

        List<Post> postList = postService.getPageList(page);

        model.addAttribute("postList", postList);
        model.addAttribute("page", page);
        return "Post/list";
    }

    @GetMapping("/insert")
    public String insertForm() {
        return "Post/insert";
    }

    @PostMapping("/insert")
    public String insert(Post post) {
        postService.register(post);
        return "redirect:/Post/list";
    }

    @GetMapping("/read")
    public String read(@RequestParam("PostNo") Long postNo, Model model) {
        Post post = postService.getPost(postNo);
        model.addAttribute("post", post);
        return "Post/read";
    }

    @GetMapping("/update")
    public String updateForm(@RequestParam("PostNo") Long postNo, Model model) {
        Post post = postService.getPost(postNo);
        model.addAttribute("post", post);
        return "Post/update";
    }

    @PostMapping("/update")
    public String update(Post post) {
        postService.modify(post);
        return "redirect:/Post/read?PostNo=" + post.getPostNo();
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("PostNo") Long postNo) {
        postService.remove(postNo);
        return "redirect:/Post/list";
    }
}