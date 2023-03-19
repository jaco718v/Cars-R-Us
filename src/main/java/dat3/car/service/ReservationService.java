package dat3.car.service;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.CarReservation;
import dat3.car.entity.Member;
import dat3.car.repository.CarRepository;
import dat3.car.repository.ReservationRepository;
import dat3.car.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

  private ReservationRepository reservationRepository;

  private MemberRepository memberRepository;

  private CarRepository carRepository;

  public ReservationService(ReservationRepository reservationRepository, MemberRepository memberRepository, CarRepository carRepository){
    this.reservationRepository = reservationRepository;
    this.memberRepository=memberRepository;
    this.carRepository=carRepository;
  }

  public ReservationResponse reserveCar(ReservationRequest rr, String username){
    if(rr.getRentalDate().isBefore(LocalDate.now())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid date, set in past");
    }
    if(rr.getRentalDate().isAfter(rr.getRentalDateEnd())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid date, end date can't be before start date");
    }
    if(checkScheduleOverlap(rr)){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid date, Schedule overlap");
    }
    Member member = memberRepository.findByUsername(username);
    Car car = carRepository.findCarById(rr.getCarId());
    CarReservation newReservation = ReservationRequest.getReservationEntity(rr,member,car);
    member.addReservation(newReservation);
    memberRepository.save(member);
    return new ReservationResponse(newReservation,true);
  }

  public List<ReservationResponse> getAllUserReservations(String username){
    List<CarReservation> carReservations = reservationRepository.findAllByMemberUsername(username);
    List<ReservationResponse> reservationResponses = carReservations.stream().map(c -> new ReservationResponse(c,true)).toList();
    return reservationResponses;
  }

  public boolean checkScheduleOverlap(ReservationRequest cr){
    List<CarReservation> carSchedule = reservationRepository.findAllById(cr.getCarId());
    for (CarReservation reservation : carSchedule){
      if (!cr.getRentalDate().isAfter(reservation.getRentalDateEnd()) && !cr.getRentalDateEnd().isBefore(reservation.getRentalDate())){
        return true;
      }
    }
    return false;
  }
}
