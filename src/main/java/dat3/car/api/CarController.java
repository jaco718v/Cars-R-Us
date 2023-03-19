package dat3.car.api;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.service.CarService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
@CrossOrigin
public class CarController {

  CarService carService;

  public CarController(CarService carService){
    this.carService=carService;
  }

  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
  @GetMapping
  public List<CarResponse> getCars(){
    return carService.getCars(true);
  }
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
  @GetMapping("/{id}")
  public CarResponse getCar(@PathVariable int id){
    return carService.getCarById(id, true);
  }

  //ADMIN
  @PreAuthorize("hasAuthority('ADMIN')")
  @PostMapping
  CarResponse addCar(@RequestBody CarRequest body){
    return carService.addCar(body);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping("/{id}")
  CarResponse updateCar(@RequestBody CarRequest body, @PathVariable int id){
    return carService.updateCar(body,id);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping("/{id}")
  void deleteCar(@PathVariable int id){
    carService.deleteCar(id);
  }
}
