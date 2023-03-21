package angeATT.Tacocloud.web.api;

import angeATT.Tacocloud.sec.Utilisateur;
import angeATT.Tacocloud.sec.UtilisateurRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/users", consumes = {MediaType.APPLICATION_JSON_VALUE})
public class UtilisateursController {
    UtilisateurRepository utilisateurRepository;
    UtilisateursController(UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository = utilisateurRepository;
    }
    @GetMapping
    Iterable<Utilisateur> users(){
        return utilisateurRepository.findAll();
    }
}
