package com.example.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    //Get all members
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        Member member = memberRepository.getMemberById(id);
        if(member == null) {
            throw new IllegalStateException("Member with Id " + id + " does not exist.");
        }
        return member;
    }

    public void deleteMember(Long id) {
        if(memberRepository.existsById(id))
            memberRepository.deleteById(id);
        else
            throw new IllegalStateException("Member with Id " + id + " does not exist.");
    }

    public Member updateMember(Long memberId, Member member) {
      Member existingMember = memberRepository.getMemberById(memberId);
      if(existingMember == null) {
          throw new IllegalStateException("Member with Id " + memberId + " does not exist.");
      }
      existingMember.setName(member.getName());
      existingMember.setEmail(member.getEmail());
      existingMember.setAddress(member.getAddress());
      existingMember.setPhoneNumber(member.getPhoneNumber());
      return memberRepository.save(existingMember);
    }
}
