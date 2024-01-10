package study.datajpa.test;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;
import study.datajpa.repository.MemberRepository;
import study.datajpa.repository.TeamRepository;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestTransactionalService testTransactionalService;
    private final TestNonTransactionalService testNonTransactionalService;
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @GetMapping("/test1")
    public void TestTransactionalFind(){
        Member member = testTransactionalService.find(2L);
        member.getTeam().getName();
    }


    @GetMapping("/test2")
    public void TestNonTransactionalFind(){
        Member member = testNonTransactionalService.find(2L);
        member.getTeam().getName();
    }

    @GetMapping("/test3")
    public void Test(){
        Member member = new Member("memberC");
        memberRepository.save(member);
    }

    @PostConstruct
    public void init(){
/*        Team team = new Team("teamA");
        teamRepository.save(team);

        Member member = new Member("memberA");
        member.changeTeam(team);
        memberRepository.save(member);*/
    }
}
