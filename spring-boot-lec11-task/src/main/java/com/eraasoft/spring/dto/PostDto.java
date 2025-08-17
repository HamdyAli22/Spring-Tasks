package com.eraasoft.spring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDto {
    private Long id;

    @NotBlank(message = "post.text.required")
    @Size(min = 20, message = "post.text.length")
    private String text;

    private String imagePath;

    private UserDto user;
}
