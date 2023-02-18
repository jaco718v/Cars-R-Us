package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.car.entity.Car;
import dat3.car.entity.CarReservation;
import dat3.car.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReservationRequest {

  private String username;

  private Integer carId;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate rentalDate;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate rentalDateEnd;

  public static CarReservation getReservationEntity(ReservationRequest rr, Member m, Car c){

    return new CarReservation(m,c,rr.getRentalDate(),rr.getRentalDateEnd());
  }

  public ReservationRequest(CarReservation cr) {
    this.username = cr.getMember().getUsername();
    this.carId = cr.getCar().getId();
    this.rentalDate = cr.getRentalDate();
    this.rentalDateEnd = cr.getRentalDateEnd();
  }
}
