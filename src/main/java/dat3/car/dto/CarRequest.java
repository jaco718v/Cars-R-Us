package dat3.car.dto;

import dat3.car.entity.Car;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarRequest {
  private String brand;
  private String model;
  private Double pricePrDay;

  public static Car getCarEntity(CarRequest c){
    return new Car(c.getBrand(),c.getModel(),c.getPricePrDay());
  }

  public CarRequest(Car c) {
    this.brand = c.getBrand();
    this.model = c.getModel();
    this.pricePrDay = c.getPricePrDay();
  }
}
