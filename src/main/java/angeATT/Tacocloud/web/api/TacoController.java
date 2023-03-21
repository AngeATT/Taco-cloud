package angeATT.Tacocloud.web.api;

import angeATT.Tacocloud.domains.Taco;
import angeATT.Tacocloud.repositories.TacoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/tacos", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "https://tacocloud:8443")
public class TacoController {
    TacoRepository tacoRepo;
    TacoController(TacoRepository tacoRepo){ //autowiring implicite
        this.tacoRepo = tacoRepo;
    }
    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos() {
        PageRequest pageRequest = PageRequest.of(
                0,12, Sort.by("createdAt").descending()
        );
        return tacoRepo.findAll(pageRequest);
    }

}
