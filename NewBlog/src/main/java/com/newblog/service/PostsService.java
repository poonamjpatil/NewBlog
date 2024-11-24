package com.newblog.service;

import com.newblog.dto.ListPostsDto;
import com.newblog.dto.PostsDto;

public interface PostsService {
    PostsDto createPosts(PostsDto postsDto);

    void deletePost(long id);

    ListPostsDto fetchAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    public PostsDto getPostsById(long id);
}
