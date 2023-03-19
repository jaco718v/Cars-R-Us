package dat3.car.api;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.service.ReservationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reservations")
@CrossOrigin
public class ReservationController {

  ReservationService reservationService;

  public ReservationController(ReservationService reservationService){
    this.reservationService=reservationService;
  }
  @PreAuthorize("hasAuthority('USER')")
  @PostMapping("/{username}")
  ReservationResponse reserveCarForMember(@PathVariable String username, @RequestBody ReservationRequest body){
    return reservationService.reserveCar(body,username);
  }

  @PreAuthorize("hasAuthority('USER')")
  @GetMapping("/{username}")
  List<ReservationResponse> getAllUserReservations(@PathVariable String username){
    return reservationService.getAllUserReservations(username);
  }
}
