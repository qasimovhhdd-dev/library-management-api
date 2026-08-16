package com.yourname.library.book;

import com.yourname.library.book.dto.BookRequestDto;
import com.yourname.library.book.dto.BookResponseDto;
import com.yourname.library.file.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "Kitablar üzərində CRUD əməliyyatları")
public class BookController {

    private final BookService bookService;



    @Operation(summary = "Yeni kitab yarat")
    @PostMapping
    public ResponseEntity<BookResponseDto> create(@Valid @RequestBody BookRequestDto dto) {
        BookResponseDto created = bookService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Bütün kitabları səhifələnmiş şəkildə göstər")
    @GetMapping
    public ResponseEntity<Page<BookResponseDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(bookService.getAll(pageable));
    }

    @Operation(summary = "ID ilə konkret kitabı göstər")
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getById(id));
    }

    @Operation(summary = "Mövcud kitabı yenilə")
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> update(@PathVariable Long id, @Valid @RequestBody BookRequestDto dto) {
        BookResponseDto updated = bookService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Kitabı sil")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Başlığa görə kitab axtar")
    @GetMapping("/search")
    public ResponseEntity<List<BookResponseDto>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookService.searchByTitle(title));
    }

    @Operation(summary = "Kateqoriyaya görə kitabları göstər")
    @GetMapping("/by-category")
    public ResponseEntity<List<BookResponseDto>> getByCategory(@RequestParam String categoryName) {
        return ResponseEntity.ok(bookService.findByCategory(categoryName));
    }
    @Operation(summary = "Dinamik filtrasiya - başlıq, ISBN, il üzrə (hamısı optional)")
    @GetMapping("/filter")
    public ResponseEntity<List<BookResponseDto>> filterBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) Integer year
    ) {
        return ResponseEntity.ok(bookService.filterBooks(title, isbn, year));
    }

    private final NotificationService notificationService;

    // constructor-a əlavə et
    public BookController(BookService bookService, NotificationService notificationService) {
        this.bookService = bookService;
        this.notificationService = notificationService;
    }

    @PostMapping("/test-async")
    public ResponseEntity<String> testAsync() {
        notificationService.sendEmailNotification("test@example.com", "Kitab uğurla yaradıldı");
        return ResponseEntity.ok("Sorğu dərhal cavablandı, email arxa fonda göndərilir");
    }
}