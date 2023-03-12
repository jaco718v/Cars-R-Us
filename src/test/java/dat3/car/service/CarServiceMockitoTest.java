package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CarServiceMockitoTest {

  @Mock
  CarRepository carRepository;

  CarService carService;

  @BeforeEach
  void setUp() {
    carService = new CarService(carRepository);
  }

  @Test
  void getCars() {
    Car c1 = new Car("Opel", "Fenix", 500,5);
    Car c2 = new Car("Citroen", "Roamer", 400,5);
    c1.setCreated(LocalDateTime.now());
    c2.setCreated(LocalDateTime.now());
    Mockito.when(carRepository.findAll()).thenReturn(List.of(c1,c2));
    List<CarResponse> cars = carService.getCars(true);
    assertEquals(2,cars.size());
    assertNotNull(cars.get(0).getCreated());
  }

  @Test
  void addCar() {
    Car c1 = new Car("Opel", "Fenix", 500,5);
    c1.setId(1);
    c1.setCreated(LocalDateTime.now());
    Mockito.when(carRepository.save(any(Car.class))).thenReturn(c1);
    CarRequest request = new CarRequest(c1);
    CarResponse response = carService.addCar(request);
    assertEquals("Opel",request.getBrand());
  }

}