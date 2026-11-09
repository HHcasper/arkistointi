package backend.arkistointi.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import backend.arkistointi.domain.Arkisto;
import backend.arkistointi.domain.ArkistoRepository;
import backend.arkistointi.domain.YritysRepository;

@Controller
public class ArkistoController {

    private final ArkistoRepository repository;
    private final YritysRepository yritysRepository;

    public ArkistoController(
            ArkistoRepository repository,
            YritysRepository yritysRepository) {
        this.repository = repository;
        this.yritysRepository = yritysRepository;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/arkistolista")
    public String bookList(Model model) {
    model.addAttribute("books", repository.findAll());
    return "booklist";
}

    @GetMapping("/lisaa")
    public String addBook(Model model) {
        model.addAttribute("book", new Arkisto());
        model.addAttribute("categories", yritysRepository.findAll());
        return "addbook";
    }

    @PostMapping("/tallenna")
    public String saveBook(@Valid @ModelAttribute("book") Arkisto book, BindingResult result, Model model) {

    if (result.hasErrors()) {
        model.addAttribute("categories", yritysRepository.findAll());
        return "addbook";
    }

    repository.save(book);
    return "redirect:/arkistolista";
}

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId) {
        repository.deleteById(bookId);
        return "redirect:/arkistolista";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        model.addAttribute("book", repository.findById(bookId).orElse(null));
        model.addAttribute("categories", yritysRepository.findAll());
        return "editbook";
    }
}