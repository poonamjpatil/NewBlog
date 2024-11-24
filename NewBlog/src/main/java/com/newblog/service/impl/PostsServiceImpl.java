package com.newblog.service.impl;

import com.newblog.dto.ListPostsDto;
import com.newblog.dto.PostsDto;
import com.newblog.entity.Posts;
import com.newblog.exception.ResourceNotFound;
import com.newblog.repository.PostsRepository;
import com.newblog.service.PostsService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PostsServiceImpl implements PostsService {

    private PostsRepository postsRepository;

    private ModelMapper modelMapper;

    public PostsServiceImpl(ModelMapper modelMapper, PostsRepository postsRepository) {
        this.modelMapper = modelMapper;
        this.postsRepository = postsRepository;
    }

    @Override
    public PostsDto createPosts(PostsDto postsDto) {
        Posts posts = mapToEntity(postsDto);
        Posts saved = postsRepository.save(posts);
        PostsDto dto = mapToPostsDto(posts);
        return dto;
    }

    @Override
    public void deletePost(long id) {
      postsRepository.deleteById(id);
    }

    public PostsDto getPostsById(long id)
    {
        Posts posts = postsRepository.findById(id).orElseThrow(
                ()->new ResourceNotFound("Post not found with id:"+id)
                );
        return mapToPostsDto(posts);
    }

    @Override
    public ListPostsDto fetchAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
       // List<Posts> posts = postsRepository.findAll();
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Posts> all = postsRepository.findAll(pageable);
        List<Posts> posts = all.getContent();
        List<PostsDto> postsDtos = posts.stream().map(p -> mapToPostsDto(p)).collect(Collectors.toList());
        ListPostsDto listPostsDto = new ListPostsDto();
        listPostsDto.setPostsDto(postsDtos);
        listPostsDto.setTotalPages(all.getTotalPages());
        listPostsDto.setTotalElements((int)all.getTotalElements());
        listPostsDto.setFirstPage(all.isFirst());
        listPostsDto.setLastPage(all.isLast());
        listPostsDto.setPageNumber(all.getNumber());
        return listPostsDto;
    }

    Posts mapToEntity(PostsDto postsDto) {
       Posts posts = modelMapper.map(postsDto, Posts.class);
       return posts;
    }

    PostsDto mapToPostsDto(Posts posts) {
      PostsDto dto = modelMapper.map(posts, PostsDto.class);
      return dto;
    }
}



