package com.newblog.service;

import com.newblog.dto.CommentDto;
import com.newblog.dto.PostsWithCommentDto;

import java.util.List;

public interface CommentsService {
    CommentDto createComment(CommentDto commentDto, long postsId);
    PostsWithCommentDto getAllCommentsByPostId(long id);
}
