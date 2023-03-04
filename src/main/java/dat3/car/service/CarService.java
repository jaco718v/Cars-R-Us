package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
  private CarRepository carRepository;

  public CarService(CarRepository carRepository){
    this.carRepository=carRepository;
  }

  public CarResponse addCar(CarRequest carRequest){
    Car newCar = CarRequest.getCarEntity(carRequest);
    newCar = carRepository.save(newCar);

    return new CarResponse(newCar, false);
  }

  public List<CarResponse> getCars(Boolean includeAll){
    List<Car> cars = carRepository.findAll();
    List<CarResponse> carResponses = cars.stream().map(c -> new CarResponse(c,includeAll)).toList();
    return carResponses;
  }

  public CarResponse getCarById(int id){
    Car car = carRepository.findCarById(id);
    CarResponse carResponse = new CarResponse(car,false);
    return carResponse;
  }
}
