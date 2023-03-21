package angeATT.Tacocloud.web.api;

import angeATT.Tacocloud.domains.Taco;
import angeATT.Tacocloud.repositories.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tacos", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "https://tacocloud:8443")
public class TacoController {
    TacoRepository tacoRepo;
    @Autowired
    TacoController(TacoRepository tacoRepo){ //autowiring implicite
        this.tacoRepo = tacoRepo;
    }
    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos() {
        PageRequest pageRequest = PageRequest.of(
                0,12, Sort.by("createdAt").descending()
        );
        return tacoRepo.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable long id ){
        Optional<Taco> taco = tacoRepo.findById(id);
        return taco.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
        return tacoRepo.save(taco);
    }

}
