package com.example.demo.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> getMemberById(Long id);
    Optional<Member> findByUserUsername(String username);

    boolean existsByUserUsername(String member);
}
