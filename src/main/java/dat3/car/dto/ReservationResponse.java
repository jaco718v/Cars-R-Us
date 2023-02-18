package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.CarReservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {
  private Integer id;
  private Integer carId;

  private String carBrand;
  private LocalDate reservationDate;

  private LocalDate rentalDate;

  private LocalDate rentalDateEnd;

  public ReservationResponse(CarReservation cr, boolean includeAll) {
    this.id = cr.getId();
    this.carId = cr.getCar().getId();
    this.carBrand= cr.getCar().getBrand();
    this.rentalDate = cr.getRentalDate();
    if(includeAll){
      this.reservationDate = cr.getReservationDate();
      this.rentalDateEnd = cr.getRentalDateEnd();
    }
  }
}
