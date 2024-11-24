package com.newblog.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListPostsDto {

    private List<PostsDto> postsDto;
    private int totalPages;
    private int totalElements;

    private boolean firstPage;
    private boolean lastPage;
    private int pageNumber;
}
