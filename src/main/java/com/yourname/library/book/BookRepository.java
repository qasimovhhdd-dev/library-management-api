package com.yourname.library.book;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByPublishedYearGreaterThan(Integer year);

    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c.name = :categoryName")
    List<Book> findByCategoryName(@Param("categoryName") String categoryName);
    @Query(value = "SELECT COUNT(*) FROM books WHERE author_id = :authorId", nativeQuery = true)
    long countBooksByAuthorId(@Param("authorId") Long authorId);
    @Query("""
    SELECT b FROM Book b
    WHERE (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')))
    AND (:isbn IS NULL OR b.isbn = :isbn)
    AND (:year IS NULL OR b.publishedYear = :year)
    """)
    List<Book> filterBooks(
            @Param("title") String title,
            @Param("isbn") String isbn,
            @Param("year") Integer year
    );

    @Query("SELECT b FROM Book b JOIN FETCH b.author")
    List<Book> findAllWithAuthor();

}
