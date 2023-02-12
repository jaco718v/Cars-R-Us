package dat3.car.api;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

  CarService carService;

  public CarController(CarService carService){
    this.carService=carService;
  }

  //ANONYMOUS
  @GetMapping
  public List<CarResponse> getCars(){
    return carService.getCars(false);
  }

  //ADMIN
  @PostMapping
  CarResponse addCar(@RequestBody CarRequest body){
    return carService.addCar(body);
  }
}
