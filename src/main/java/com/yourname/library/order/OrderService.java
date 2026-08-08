package com.yourname.library.order;

import com.yourname.library.book.Book;
import com.yourname.library.book.BookRepository;
import com.yourname.library.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    public OrderService(OrderRepository orderRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }
    @Transactional
    public Order createOrder(List<Long> bookIds, List<Integer> quantities) {
        Order order = new Order();

        for (int i = 0; i < bookIds.size(); i++) {Book book = bookRepository.findById(bookIds.get(i))
                    .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

            OrderItem item = new OrderItem();
            item.setOrder(order);
              item.setBook(book);
            item.setQuantity(quantities.get(i));
            order.getItems().add(item);
        }

        return orderRepository.save(order);
    }
}