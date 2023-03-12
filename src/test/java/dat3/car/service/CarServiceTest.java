package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarServiceTest {

  @Autowired
  public CarRepository carRepository;

  CarService carService;

  boolean dataIsReady = false;

  @BeforeEach
  void setUp() {
    if(!dataIsReady){
      carRepository.saveAndFlush(new Car("Opel", "Fenix", 500,5));
      carRepository.saveAndFlush(new Car("Citroen", "Roamer", 400,5));
      dataIsReady = true;
      carService = new CarService(carRepository);
    }
  }

  @Test
  void addCar() {
    Car newCar = new Car("Volkswagen", "Up", 700,5);
    CarRequest newCarRequest = new CarRequest(newCar);
    carService.addCar(newCarRequest);
    List<CarResponse> cars = carService.getCars(true);
    assertEquals(3,cars.size());
  }

  @Test
  void getCars() {
    List<CarResponse> cars = carService.getCars(true);
    assertEquals(2,cars.size());
    assertNotNull(cars.get(0).getCreated());
  }
}