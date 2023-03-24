package angeATT.Tacocloud.web.api;

import angeATT.Tacocloud.domains.Taco;
import angeATT.Tacocloud.domains.TacoOrder;
import angeATT.Tacocloud.repositories.OrderRepository;
import angeATT.Tacocloud.repositories.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tacos", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoController {
    TacoRepository tacoRepo;
    OrderRepository orderRepo;
    @Autowired
    TacoController(TacoRepository tacoRepo, OrderRepository orderRepo){ //autowiring implicite

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
    @PatchMapping(path="/{orderId}", consumes="application/json")
    public TacoOrder patchOrder(@PathVariable("orderId") Long orderId,
                                @RequestBody TacoOrder patch) {
        TacoOrder order = orderRepo.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return orderRepo.save(order);
    }
    @DeleteMapping(path = "/{orderId}",consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteOrder(@PathVariable("orderId") Long orderId){
        try{
             orderRepo.deleteById(orderId);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }

}
