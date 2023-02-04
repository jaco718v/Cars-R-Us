package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class DeveloperData implements ApplicationRunner {

  final CarRepository carRepository;
  final MemberRepository memberRepository;

  public DeveloperData(CarRepository carRepository, MemberRepository memberRepository){
    this.carRepository = carRepository;
    this.memberRepository = memberRepository;
  }

  private final String passwordUsedByAll = "test12";

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Member m1 = new Member("member1", passwordUsedByAll, "memb1@a.dk", "Kurt", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");
    Member m2 = new Member("member2", passwordUsedByAll, "aaa@dd.dk", "Hanne", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");
    m1.setFavoriteCarColors(new ArrayList<String>(Arrays.asList("Blue","Red")));
    m2.setFavoriteCarColors(new ArrayList<String>(Arrays.asList("Green","Red")));
    HashMap<String,String> map1 = new HashMap<String,String>();
    map1.put("home","11111111"); map1.put("mobile","22222222");
    m1.setPhones(map1);
    memberRepository.save(m1);
    memberRepository.save(m2);
    Car c1 = new Car("Opel", "Fenix", 500, 20);
    Car c2 = new Car("Critroen", "Roamer", 400, 25);
    carRepository.save(c1);
    carRepository.save(c2);
  }

}
