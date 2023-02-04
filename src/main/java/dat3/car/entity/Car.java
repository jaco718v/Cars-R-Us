package dat3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "car_brand", length = 50, nullable = false)
  private String brand;
  @Column(name = "car_model", length = 60, nullable = false)
  private String model;
  @Column(name = "rental_price_day")
  private double pricePrDay;
  @Column(name = "max_discount")
  private int bestDiscount;
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime created = LocalDateTime.now();
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime lastEdited = LocalDateTime.now();

  public Car(String brand, String model, double pricePrDay, int bestDiscount) {
    this.brand = brand;
    this.model = model;
    this.pricePrDay = pricePrDay;
    this.bestDiscount = bestDiscount;
  }
}
