package angeATT.Tacocloud.Controllers;

import angeATT.Tacocloud.domains.TacoOrder;
import angeATT.Tacocloud.repositories.OrderRepository;
import angeATT.Tacocloud.sec.Utilisateur;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    OrderRepository orderRepository;
    @Autowired
    OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    @GetMapping("/current")
    public String showCurrentOrder(){
        return "orderForm";
    }
    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus,Authentication authentication) {
        if ( errors.hasErrors() ){
            return "orderForm";
        }
        order.setUser( (Utilisateur) authentication.getPrincipal());
        orderRepository.save(order);

        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

}
