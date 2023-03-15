package angeATT.Tacocloud.sec;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur,Long> {
    Utilisateur findByUsername(String username);
}
