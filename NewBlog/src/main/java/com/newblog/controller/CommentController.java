package com.newblog.controller;

import com.newblog.dto.CommentDto;
import com.newblog.dto.PostsWithCommentDto;
import com.newblog.service.CommentsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor

public class CommentController {

    private CommentsService commentsService;

    //http://localhost:8080/api/comments/1

    @PostMapping("/{postsId}")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable long postsId){

        CommentDto dto = commentsService.createComment(commentDto, postsId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @GetMapping("/{postsId}")
    public ResponseEntity<PostsWithCommentDto> getAllCommentsByPostId(@PathVariable long
                                                   postsId)
    {
        PostsWithCommentDto allCommentsByPostId = commentsService.getAllCommentsByPostId(postsId);
        return new ResponseEntity<>(allCommentsByPostId,HttpStatus.OK);
    }
}
