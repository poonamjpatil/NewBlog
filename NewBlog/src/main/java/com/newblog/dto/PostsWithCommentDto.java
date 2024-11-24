package com.newblog.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PostsWithCommentDto {

    private PostsDto posts;
    private List<CommentDto> commentDto = new ArrayList<>();
}
