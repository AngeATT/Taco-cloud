package angeATT.Tacocloud.repositories;

import angeATT.Tacocloud.domains.TacoOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


public interface OrderRepository extends CrudRepository<TacoOrder, Long> {


    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);

}
