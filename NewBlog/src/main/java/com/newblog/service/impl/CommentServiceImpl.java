package com.newblog.service.impl;

import com.newblog.dto.CommentDto;
import com.newblog.dto.PostsDto;
import com.newblog.dto.PostsWithCommentDto;
import com.newblog.entity.Comment;
import com.newblog.entity.Posts;
import com.newblog.repository.CommentRepository;
import com.newblog.repository.PostsRepository;
import com.newblog.service.CommentsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentsService {

    private CommentRepository commentRepository;
    private ModelMapper modelMapper;
    private PostsRepository postsRepository;


    @Override
    public CommentDto createComment(CommentDto commentDto, long postsId) {
        Optional<Posts> byId = postsRepository.findById(postsId);
        Posts posts = byId.get();
        Comment comment = mapToEntity(commentDto);
        comment.setPosts(posts);
        Comment savedComment = commentRepository.save(comment);
        CommentDto dto = mapToDto(savedComment);
        return dto;
    }

    @Override
    public PostsWithCommentDto getAllCommentsByPostId(long id)
    {
        Posts posts = postsRepository.findById(id).get();

        PostsDto dto = new PostsDto();
        dto.setId(posts.getId());
        dto.setTitle(posts.getTitle());
        dto.setDescription(posts.getDescription());
        dto.setContent(posts.getContent());


        List<Comment> comments = commentRepository.findByPostsId(id);
        List<CommentDto> dtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        PostsWithCommentDto postsWithCommentDto = new PostsWithCommentDto();

        postsWithCommentDto.setCommentDto(dtos);
        postsWithCommentDto.setPosts(dto);

        return postsWithCommentDto;

    }

    Comment mapToEntity(CommentDto dto)
    {
        Comment comment = modelMapper.map(dto, Comment.class);
        return comment;
    }

    CommentDto mapToDto(Comment comment)
    {
        return modelMapper.map(comment, CommentDto.class);
    }
}
