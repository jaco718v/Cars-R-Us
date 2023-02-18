package dat3.car.repository;

import dat3.car.entity.CarReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ReservationRepository extends JpaRepository<CarReservation,Integer> {
  ArrayList<CarReservation> findAllById(Integer carId);

}
