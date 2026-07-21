package com.yourname.library.author;
import com.yourname.library.book.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity // Bu class-ın verilənlər bazasında cədvələ uyğun olduğunu bildirir
@Table(name="authors") // Cədvəlin adını göstərir
@Getter // Lombok: bütün field-lər üçün avtomatik getter-lər yaradır
@Setter // Lombok: bütün field-lər üçün avtomatik setter-lər yaradır

public class Author {

    @Id // Bu field-in(id) primary key olduğunu bildirir
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id-ni DB özü avtomatik artıraraq versin,yeni SERIAL
    private long id;

    private String name;

    private String nationality;

    // Bir Author-un çox Book-u ola bilər; "author" - Book class-ındakı əlaqəni saxlayan field; cascade - Author silinəndə bağlı Book-lar da silinsin
    @OneToMany(mappedBy = "author" ,cascade = CascadeType.ALL)
    private List<Book> book;
}
