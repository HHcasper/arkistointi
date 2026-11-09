package backend.arkistointi.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import backend.arkistointi.domain.Arkisto;
import backend.arkistointi.domain.ArkistoRepository;

@RestController
public class ArkistoRestController {

    private final ArkistoRepository repository;

    public ArkistoRestController(ArkistoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/arkistot")
public Iterable<Arkisto> getArkistot() {
    return repository.findAll();
}

@GetMapping("/api/arkistot/{id}")
public Arkisto getArkisto(@PathVariable("id") Long id) {
    return repository.findById(id).orElse(null);
}
}