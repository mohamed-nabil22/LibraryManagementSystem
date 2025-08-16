package com.example.demo.Member;

import com.example.demo.User.User;
import jakarta.transaction.Transactional;
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

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @GetMapping("/all")
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @PreAuthorize("hasRole('MEMBER')")
    @Transactional
    @GetMapping(path = "/getInfo")
    public ResponseEntity<MemberInfoDto> getMemberInfo(Authentication auth) {
        String name = auth.getName();
        Member m = memberService.findByUserUsername(name);
        String username = m.getUser().getUsername();
        Long id = m.getUser().getId();
        MemberInfoDto  memberInfoDto = new MemberInfoDto();
        memberInfoDto.setId(id);
        memberInfoDto.setAddress(m.getAddress());
        memberInfoDto.setEmail(m.getEmail());
        memberInfoDto.setPhoneNumber(m.getPhoneNumber());
        memberInfoDto.setUserName(username);
        return ResponseEntity.ok(memberInfoDto);
    }

    @PreAuthorize("hasRole('MEMBER')")
    @PutMapping("/updateInfo")
    public ResponseEntity<Member> updateMember(Authentication auth, @RequestBody Member updatedMember) {

        Member member = memberService.findByUserUsername(auth.getName());
        return ResponseEntity.ok(memberService.updateMember(member.getId(), updatedMember));
    }
    @PostMapping("/create")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        Member newMember = memberService.save(member);
        return ResponseEntity.ok(newMember);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path="/rem/{memberId}")
    public ResponseEntity<String> deleteMember(@PathVariable("memberId") Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok("Member deleted successfully");
    }

}
