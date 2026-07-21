package com.yourname.library.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestDto {

    @NotBlank(message = "title bos ola bilmez")
    private String title;

    @NotBlank(message = "isbn bos ola bilmez")
    private String isbn;

    @NotNull(message = "Nəşr ili göstərilməlidir")
    private Integer publicationYear;

    @NotNull(message = "Author ID göstərilməlidir")
    private Long authorId;
}
