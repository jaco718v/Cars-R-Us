package dat3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class CarReservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  private Member member;

  @ManyToOne
  private Car car;

  @CreationTimestamp
  private LocalDate reservationDate;

  private LocalDate rentalDate;

  private LocalDate rentalDateEnd;

  public CarReservation(Member member, Car car, LocalDate rentalDate, LocalDate rentalDateEnd) {
    this.member = member;
    this.car = car;
    this.rentalDate = rentalDate;
    this.rentalDateEnd = rentalDateEnd;
  }
}
