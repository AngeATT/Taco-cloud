package angeATT.Tacocloud.repositories;

import angeATT.Tacocloud.domains.Taco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;


public interface TacoRepository extends CrudRepository<Taco,Long> {
}
