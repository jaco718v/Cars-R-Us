package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

  public CarResponse updateCar(CarRequest carRequest, int id){
    if(!carRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No car with such ID exists");
    }
      Car updatingCar = carRepository.findCarById(id);
      if (null != carRequest.getBrand()) {
        updatingCar.setBrand(carRequest.getBrand());
      }
      if (null != carRequest.getModel()) {
        updatingCar.setModel(carRequest.getModel());
      }
      if (null != carRequest.getPricePrDay()) {
        updatingCar.setPricePrDay(carRequest.getPricePrDay());
      }
      carRepository.save(updatingCar);

      return new CarResponse(updatingCar, false);
  }

  public void deleteCar(int id){
    if(!carRepository.existsById(id)){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No car with such ID exists");
    }
      carRepository.deleteById(id);
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
