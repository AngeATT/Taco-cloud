package angeATT.Tacocloud.repositories;

import angeATT.Tacocloud.domains.TacoOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
