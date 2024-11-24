package com.newblog.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class PostsDto {
    private long id;
    @NotEmpty
    @Size(min = 3, message = "Title should be atleast 3 character")
    private String title;
    @NotEmpty
    @Size(min=3,message = "description should be at least 3 character")
    private String description;
    private String content;
}
