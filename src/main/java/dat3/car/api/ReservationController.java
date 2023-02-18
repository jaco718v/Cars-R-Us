package dat3.car.api;

import dat3.car.dto.ReservationRequest;
import dat3.car.service.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

  ReservationService reservationService;

  public ReservationController(ReservationService reservationService){
    this.reservationService=reservationService;
  }
  @PostMapping("/{username}/reservation")
  void reserveCarForMember(@PathVariable String username, @RequestBody ReservationRequest body){
    reservationService.reserveCar(body,username);
  }
}
