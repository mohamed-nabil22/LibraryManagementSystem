package com.example.demo.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        Member newMember = memberService.save(member);
        return ResponseEntity.ok(newMember);
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @PreAuthorize("hasRole('MEMBER')")
    @GetMapping(path = "member/getInfo")
    public ResponseEntity<Member> getMemberInfo(Authentication auth) {
        return ResponseEntity.ok(memberService.findByUserUsername(auth.getName()));
    }

    @PutMapping("/member/updateInfo")
    public ResponseEntity<Member> updateMember(Authentication auth, @RequestBody Member updatedMember) {
        Member member = memberService.findByUserUsername(auth.getName());
        return ResponseEntity.ok(memberService.updateMember(member.getId(), updatedMember));
    }

    @DeleteMapping("/member/delteProfile")
    public ResponseEntity<String> deleteMember(Authentication auth) {
        Member member = memberService.findByUserUsername(auth.getName());
        memberService.deleteMember(member.getId());
        return ResponseEntity.ok("Member deleted successfully");
    }




}
