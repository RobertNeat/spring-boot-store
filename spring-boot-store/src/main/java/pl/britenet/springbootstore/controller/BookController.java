package pl.britenet.springbootstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.store.model.Book;
import pl.britenet.store.service.BookService;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://127.0.0.1:5500")
@RestController
@RequestMapping("api/v2/book")
public class BookController {
    public final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService=bookService;
    }

    @GetMapping("/{book_id}")
    public Optional<Book> getBook(@PathVariable("book_id") int id){
        return this.bookService.getBook(id);
    }

    @GetMapping
    public List<Book> getAllBook(){
        return this.bookService.getAllBook();
    }

    @GetMapping("/max")
    public Optional<Integer> getMaxId(){
        return this.bookService.getMaxId();
    }

    @PostMapping
    public void insert(@RequestBody Book book){
        this.bookService.insert(book);
    }

    @PutMapping
    public void update(@RequestBody Book book){
        this.bookService.update(book);
    }

    @DeleteMapping("/{book_id}")
    public void delete(@PathVariable("book_id") int id){
        this.bookService.delete(id);
    }

}
