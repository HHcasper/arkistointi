package backend.arkistointi.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import backend.arkistointi.domain.Yritys;
import backend.arkistointi.domain.YritysRepository;

@Controller
public class YritysController {

    private final YritysRepository yritysRepository;

    public YritysController(YritysRepository yritysRepository) {
        this.yritysRepository = yritysRepository;
    }

    @GetMapping("/yritykset")
    public String yritysLista(Model model) {
    model.addAttribute("categories", yritysRepository.findAll());
    return "categorylist";
    }

    @GetMapping("/lisaayritys")
    public String lisaaYritys(Model model) {
    model.addAttribute("category", new Yritys());
    return "addcategory";
    }

    @PostMapping("/tallennaYritys")
    public String tallennaYritys(Yritys yritys) {
    yritysRepository.save(yritys);
    return "redirect:/yritykset";
    }
}