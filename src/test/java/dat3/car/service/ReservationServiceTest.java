package dat3.car.service;

import dat3.car.dto.ReservationRequest;
import dat3.car.entity.Car;
import dat3.car.entity.CarReservation;
import dat3.car.entity.Member;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReservationServiceTest {

  @Autowired
  public ReservationRepository reservationRepository;

  @Autowired
  public MemberRepository memberRepository;

  @Autowired
  public CarRepository carRepository;

  ReservationService reservationService;

  boolean dataIsReady = false;

  @BeforeEach
  void setUp() {
    if (!dataIsReady) {  //Explain this
      carRepository.save(new Car("Opel", "Fenix", 500,5));
      dataIsReady = true;
      reservationService = new ReservationService(reservationRepository, memberRepository, carRepository);
    }
  }

  @Test
  void reserveCar() {
  }

  @Test
  void checkScheduleOverlap() {
    Car c1 = carRepository.findCarById(1);
    CarReservation cr1 = new CarReservation();
    cr1.setRentalDate(LocalDate.parse("2023-01-01"));
    cr1.setRentalDateEnd(LocalDate.parse("2023-03-03"));
    cr1.setCar(c1);
    reservationRepository.save(cr1);

    CarReservation cr2 = new CarReservation();
    cr2.setRentalDate(LocalDate.parse("2023-06-06"));
    cr2.setRentalDateEnd(LocalDate.parse("2023-09-09"));
    cr2.setCar(c1);
    reservationRepository.save(cr2);


    ReservationRequest cr3 = new ReservationRequest();
    cr3.setRentalDate(LocalDate.parse("2023-04-04"));
    cr3.setRentalDateEnd(LocalDate.parse("2023-05-05"));
    cr3.setCarId(1);
    assertFalse(reservationService.checkScheduleOverlap(cr3));

    ReservationRequest cr4 = new ReservationRequest();
    cr4.setRentalDate(LocalDate.parse("2023-02-02"));
    cr4.setRentalDateEnd(LocalDate.parse("2023-05-05"));
    cr4.setCarId(1);
    assertTrue(reservationService.checkScheduleOverlap(cr4));

  }

}