package study.datajpa.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    @ResponseBody
    public String findMember(@PathVariable("id")Long id){
        Optional<Member> member = memberRepository.findById(id);
        return member.get().getUsername();
    }

    @GetMapping("/members2/{id}")
    @ResponseBody
    public String findMember2(@PathVariable("id")Member member){
        return member.getUsername();
    }

    @GetMapping("/members")
    public Page<MemberDto> list(@PageableDefault(size = 10) Pageable pageable){
        return memberRepository.findAll(pageable).map(member -> new MemberDto(member.getId(), member.getUsername(), null));
    }

    @PostConstruct
    public void init(){
/*        for(int i = 0 ; i < 100 ; i++){
            memberRepository.save(new Member("user" + i, i));
        }*/
    }
}
