package fi.haagahelia.bookstorepalinaleksi.web;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.bookstorepalinaleksi.domain.Book;
import fi.haagahelia.bookstorepalinaleksi.domain.BookRepository;
import fi.haagahelia.bookstorepalinaleksi.domain.CategoryRepository;



@Controller 
public class BookController {

    private BookRepository repository;
    private CategoryRepository crepository;

    public BookController(BookRepository repository,CategoryRepository crepository){
        this.repository = repository;
        this.crepository = crepository;
    }


    @RequestMapping("/booklist")
    public String booklist(Model model){
        model.addAttribute("books",repository.findAll());
        return "booklist.html";
    }


    @RequestMapping("/add")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories", crepository.findAll());
        return "addbook.html";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:/booklist";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model){
        repository.deleteById(bookId);
        return "redirect:/booklist";
    }

    @RequestMapping(value = "/edit/{id}")
    public String showdModBook(@PathVariable("id") Long bookId, Model model){
        model.addAttribute("book", repository.findById(bookId));
        model.addAttribute("categories", crepository.findAll());
        return "modifybook.html";
    }
}
