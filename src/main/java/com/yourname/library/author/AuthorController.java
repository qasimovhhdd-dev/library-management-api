package com.yourname.library.author;

import com.yourname.library.author.dto.AuthorRequestDto;
import com.yourname.library.author.dto.AuthorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Authors", description = "Müəlliflər üzərində CRUD əməliyyatları")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Operation(summary = "Yeni müəllif yarat")
    @PostMapping
    public ResponseEntity<AuthorResponseDto> create(@Valid @RequestBody AuthorRequestDto dto) {
        AuthorResponseDto created = authorService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Bütün müəllifləri göstər")
    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> getAll() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @Operation(summary = "ID ilə konkret müəllifi göstər")
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getById(id));
    }

    @Operation(summary = "Mövcud müəllifi yenilə")
    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> update(@PathVariable Long id, @Valid @RequestBody AuthorRequestDto dto) {
        AuthorResponseDto updated = authorService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Müəllifi sil")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}