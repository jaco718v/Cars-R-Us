package dat3.car.api;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.dto.ReservationRequest;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import dat3.car.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
@CrossOrigin
class MemberController {

  MemberService memberService;

  public MemberController(MemberService memberService){
    this.memberService=memberService;
  }

  //ADMIN ONLY
  @GetMapping
  List<MemberResponse> getMembers(){
    return memberService.getMembers(true);}

  //ADMIN ONLY
  @GetMapping(path = "/{username}")
  MemberResponse getMemberById(@PathVariable String username) throws Exception {
    return null;}

  //ANONYMOUS
  //@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @PostMapping
  MemberResponse addMember(@RequestBody MemberRequest body){
    return memberService.addMember(body);
  }

  //MEMBER
  @PutMapping("/{username}")
  MemberResponse editMember(@RequestBody MemberRequest body, @PathVariable String username){
    return memberService.updateMember(body,username);
  }

  //ADMIN ONLY
  @PatchMapping("/ranking/{username}/{value}")
  void setRankingForUser(@PathVariable String username, @PathVariable int value) {}

  //ADMIN
  @DeleteMapping("/{username}")
  void deleteMemberByUsername(@PathVariable String username) {
    memberService.deleteMember(username);
  }


}

