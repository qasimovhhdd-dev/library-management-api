package com.yourname.library.author;

import com.yourname.library.author.dto.AuthorRequestDto;
import com.yourname.library.author.dto.AuthorResponseDto;
import com.yourname.library.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorResponseDto create(AuthorRequestDto dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setNationality(dto.getNationality());

        Author saved = authorRepository.save(author);
        return toResponseDto(saved);
    }

    public AuthorResponseDto update(Long id, AuthorRequestDto dto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        author.setName(dto.getName());
        author.setNationality(dto.getNationality());

        Author updated = authorRepository.save(author);
        return toResponseDto(updated);
    }

    public List<AuthorResponseDto> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .toList();
    }

    public AuthorResponseDto getById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        return toResponseDto(author);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    private AuthorResponseDto toResponseDto(Author author) {
        return new AuthorResponseDto(
                author.getId(),
                author.getName(),
                author.getNationality()
        );
    }
}