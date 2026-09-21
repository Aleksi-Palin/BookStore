package fi.haagahelia.bookstorepalinaleksi.web;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.bookstorepalinaleksi.domain.Book;
import fi.haagahelia.bookstorepalinaleksi.domain.BookRepository;

@RestController 
public class BookRestController {

    private BookRepository repository;

    public BookRestController(BookRepository repository){
        this.repository = repository;
    }

    @RequestMapping(value = "/jsonBooklist",method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest(){
        return (List<Book>) repository.findAll();
    }

    @RequestMapping(value = "/bookById/{id}",method = RequestMethod.GET)
    public @ResponseBody Book bookById(@PathVariable("id") Long bookId){
        return repository.findById(bookId).orElse(null);
    }
}
