package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MemberResponse {
  String username; //Remember this is the primary key
  String email;
  String firstName;
  String lastName;
  String street;
  String city;
  String zip;

  List<ReservationResponse> reservations = new ArrayList<>();
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
  LocalDateTime created;

  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
  LocalDateTime edited;
  Integer ranking;
  Boolean approved;

  //Convert Member Entity to Member DTO
  public MemberResponse(Member m, boolean includeAll) {
    this.username = m.getUsername();
    this.email = m.getEmail();
    this.street = m.getStreet();
    this.firstName =m.getFirstName();
    this.lastName = m.getLastName();
    this.city = m.getCity();
    this.zip = m.getZip();
    this.reservations = m.getReservations().stream().map(r-> new ReservationResponse(r,includeAll)).toList();
    if(includeAll){
      this.created = m.getCreated();
      this.edited = m.getLastEdited();
      this.approved = m.isApproved();
      this.ranking = m.getRanking();
    }
  }
}

