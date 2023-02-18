package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberServiceTest {

  @Autowired
  public MemberRepository memberRepository;


  MemberService memberService;


  boolean dataIsReady = false;

  @BeforeEach
  void setUp() {
    if (!dataIsReady) {  //Explain this
      memberRepository.save(new Member("m1", "test12", "m1@a.dk", "bb", "Olsen", "xx vej 34", "Lyngby", "2800"));
      memberRepository.save(new Member("m2", "test12", "m2@a.dk", "aa", "hansen", "xx vej 34", "Lyngby", "2800"));
      dataIsReady = true;
      memberService = new MemberService(memberRepository); //Real DB is mocked away with H2
    }
  }

  @Test
  void addMember() {
    Member newMember = new Member("m3", "test12", "m3@a.dk", "cc", "Bobsen", "xx vej 34", "Lyngby", "2800");
    MemberRequest newMemberRequest = new MemberRequest(newMember);
    memberService.addMember(newMemberRequest);
    List<MemberResponse> members = memberService.getMembers(true);
    assertEquals(3, members.size());
  }

  @Test
  void getMembersAdmin() {
    List<MemberResponse> members = memberService.getMembers(true);
    assertEquals(2, members.size());
    System.out.println(members.get(0).getCreated());
    //assertNotNull(members.get(0).getCreated());
  }
}