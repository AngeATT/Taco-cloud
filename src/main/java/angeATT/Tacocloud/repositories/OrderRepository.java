package angeATT.Tacocloud.repositories;

import angeATT.Tacocloud.domains.TacoOrder;
import org.springframework.stereotype.Repository;


public interface OrderRepository {
    TacoOrder save(TacoOrder order);


}
