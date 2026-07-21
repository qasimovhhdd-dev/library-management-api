package com.yourname.library.author.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorResponseDto {
    private Long id;
    private String name;
    private String nationality;
}