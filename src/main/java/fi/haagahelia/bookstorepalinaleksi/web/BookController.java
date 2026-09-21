package fi.haagahelia.bookstorepalinaleksi.web;


import java.security.Principal;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
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

    @RequestMapping("/")
    public String defaultTo(){
        return "home.html";
    }

    @RequestMapping("/home")
    public String home(){
        return "home.html";
    }

    @RequestMapping("/login")
    public String login(){
        return "login.html";
    }

    @RequestMapping("/booklist")
    public String booklist(Model model, Principal principal, HttpSession session){
        session.setAttribute("username", principal.getName());
        model.addAttribute("books",repository.findAll());
        model.addAttribute("username", session.getAttribute("username"));
        return "booklist.html";
    }


    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories", crepository.findAll());
        return "addbook.html";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String save(Book book){
        repository.save(book);
        return "redirect:/booklist";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteBook(@PathVariable("id") Long bookId, Model model){
        repository.deleteById(bookId);
        return "redirect:/booklist";
    }

    @RequestMapping(value = "/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showdModBook(@PathVariable("id") Long bookId, Model model){
        model.addAttribute("book", repository.findById(bookId));
        model.addAttribute("categories", crepository.findAll());
        return "modifybook.html";
    }
}
