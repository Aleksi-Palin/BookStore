package fi.haagahelia.bookstorepalinaleksi.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.haagahelia.bookstorepalinaleksi.domain.BookRepository;



@Controller
public class BookController {

    private BookRepository repository;

    public BookController(BookRepository repository){
        this.repository = repository;
    }


    @RequestMapping("/booklist")
    public String booklist(Model model){
        model.addAttribute("books",repository.findAll());
        return "booklist.html";
    }
}
