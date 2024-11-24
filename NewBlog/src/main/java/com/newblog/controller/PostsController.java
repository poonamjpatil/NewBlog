package com.newblog.controller;


import com.newblog.dto.ListPostsDto;
import com.newblog.dto.PostsDto;
import com.newblog.service.PostsService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostsController {

    private PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    //http:localhost:8080/api/posts

    @PostMapping
    public ResponseEntity<?> createPosts(@Valid @RequestBody PostsDto postsDto, BindingResult bindingResult) {
        PostsDto dto = postsService.createPosts(postsDto);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) {
        postsService.deletePost(id);
        return new ResponseEntity<>("Post Deleted successfully", HttpStatus.OK);
    }


    //http://localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=description&sortDir=desc
    @GetMapping
    public ResponseEntity<ListPostsDto>fetchAllPosts(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false)int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false)int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "title", required = false)String sortBy,
            @RequestParam(name= "sortDir", defaultValue = "desc", required = false)String sortDir
    )
    {
        ListPostsDto postsDtos = postsService.fetchAllPosts(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postsDtos,HttpStatus.OK);

    }

    //http://localhost:8080/api/posts/1
    @GetMapping("/{id}")
    public ResponseEntity<PostsDto> getPostsById(@PathVariable long id)
    {
        PostsDto dto = postsService.getPostsById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }

}


