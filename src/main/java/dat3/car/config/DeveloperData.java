package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.CarReservation;
import dat3.car.entity.Member;
import dat3.car.repository.CarRepository;
import dat3.car.repository.ReservationRepository;
import dat3.car.repository.MemberRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.*;

@Controller
public class DeveloperData implements ApplicationRunner {

  final CarRepository carRepository;
  final MemberRepository memberRepository;
  final ReservationRepository reservationRepository;

  public DeveloperData(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository){
    this.carRepository = carRepository;
    this.memberRepository = memberRepository;
    this.reservationRepository = reservationRepository;
  }

  private final String passwordUsedByAll = "test12";

  public void makeTestData(){
    Member m1 = new Member("member1", passwordUsedByAll, "memb1@a.dk", "Kurt", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");
    Member m2 = new Member("member2", passwordUsedByAll, "aaa@dd.dk", "Hanne", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");
    m1.setFavoriteCarColors(new ArrayList<String>(Arrays.asList("Blue","Red")));
    m2.setFavoriteCarColors(new ArrayList<String>(Arrays.asList("Green","Red")));
    HashMap<String,String> map1 = new HashMap<String,String>();
    map1.put("home","11111111"); map1.put("mobile","22222222");
    m1.setPhones(map1);

    Car c1 = new Car("Opel", "Fenix", 500);
    Car c2 = new Car("Critroen", "Roamer", 400);

    carRepository.save(c1);
    carRepository.save(c2);

    memberRepository.save(m1);
    memberRepository.save(m2);

    CarReservation r1 = new CarReservation(m1,c1, LocalDate.now(),LocalDate.now());

    m1.addReservation(r1);
    c1.addReservation(r1);

    carRepository.save(c1);





  }

  public void makeReservationData(){

  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    //makeTestData();
    Member m1 = new Member("member1", passwordUsedByAll, "memb1@a.dk", "Kurt", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");
    Car c1 = new Car("Opel", "Fenix", 500);
    carRepository.save(c1);
    memberRepository.save(m1);
    setupUserWithRoleUsers();


  }


  @Autowired
  UserWithRolesRepository userWithRolesRepository;

  /*****************************************************************************************
   NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
   iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
   *****************************************************************************************/
  private void setupUserWithRoleUsers() {
    System.out.println("******************************************************************************");
    System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
    System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
    System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
    System.out.println("******************************************************************************");
    UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
    UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
    UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
    UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
    user1.addRole(Role.USER);
    user1.addRole(Role.ADMIN);
    user2.addRole(Role.USER);
    user3.addRole(Role.ADMIN);
    //No Role assigned to user4
    userWithRolesRepository.save(user1);
    userWithRolesRepository.save(user2);
    userWithRolesRepository.save(user3);
    userWithRolesRepository.save(user4);
  }


}
