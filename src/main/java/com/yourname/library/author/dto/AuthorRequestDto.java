package com.yourname.library.author.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorRequestDto {
    @NotBlank(message = "Ad boş ola bilməz")
    private String name;

    @NotBlank(message = "Millilik boş ola bilməz")
    private String nationality;
}