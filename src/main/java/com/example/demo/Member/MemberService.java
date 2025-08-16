package com.example.demo.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public Member findByUserUsername(String username) {
        Optional<Member> member = memberRepository.findByUserUsername(username);
        if(member.isEmpty()) {
            throw new IllegalStateException("Member with Username " + username + " does not exist.");
        }
        return member.get();
    }
    //Get all members
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        Optional<Member> member = memberRepository.getMemberById(id);
        if(member.isEmpty()) {
            throw new IllegalStateException("Member with Id " + id + " does not exist.");
        }
        return member.get();
    }

    public void deleteMember(Long id) {
        if(memberRepository.existsById(id))
            memberRepository.deleteById(id);
        else
            throw new IllegalStateException("Member with Id " + id + " does not exist.");
    }

    public Member updateMember(Long memberId, Member member) {
      Optional<Member> existingMember = memberRepository.getMemberById(memberId);
      if(existingMember.isEmpty()) {
          throw new IllegalStateException("Member with Id " + memberId + " does not exist.");
      }

      existingMember.get().setEmail(member.getEmail());
      existingMember.get().setAddress(member.getAddress());
      existingMember.get().setPhoneNumber(member.getPhoneNumber());
      return memberRepository.save(existingMember.get());
    }
}
