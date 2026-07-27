package com.yourname.library.book;

import com.yourname.library.author.Author;
import com.yourname.library.author.AuthorRepository;
import com.yourname.library.book.dto.BookRequestDto;
import com.yourname.library.book.dto.BookResponseDto;
import com.yourname.library.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public BookResponseDto create(BookRequestDto dto) {
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPublishedYear(dto.getPublicationYear());
        book.setAuthor(author);

        Book saved = bookRepository.save(book);
        return toResponseDto(saved);
    }
    public BookResponseDto update(Long id, BookRequestDto dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setPublishedYear(dto.getPublicationYear());
        book.setAuthor(author);

        Book updated = bookRepository.save(book);
        return toResponseDto(updated);
    }

    public Page<BookResponseDto> getAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(this::toResponseDto);
    }

    public BookResponseDto getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return toResponseDto(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    private BookResponseDto toResponseDto(Book book) {
        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getPublishedYear(),
                book.getAuthor().getName()
        );
    }
}