package study.datajpa.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class TestTransactionalService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member find(Long id){
        Member member = memberRepository.findById(id).get();
        member.getTeam().getName();
        return member;
    }
}
