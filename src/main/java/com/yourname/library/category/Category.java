package com.yourname.library.category;

import com.yourname.library.book.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

 import java.util.HashSet;
import java.util.Set;

  @Entity
@Table(name = "categories")
@Setter
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Book> books = new HashSet<>();
}