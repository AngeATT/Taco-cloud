package angeATT.Tacocloud.domains;

import lombok.Data;

import java.util.List;

@Data
public class TacoOrder {
    private String deliveryName;
    private String StringStreet;
    private String deliveryCity;
    private String deliveryState;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
    private List<Taco> tacos;

    public void addTaco(Taco taco){
        this.tacos.add(taco);
    }
}
