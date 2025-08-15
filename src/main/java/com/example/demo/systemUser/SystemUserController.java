package com.example.demo.systemUser;

import com.example.demo.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class SystemUserController {
    @Autowired
    private SystemUserService systemUserService;

    @PostMapping
    public SystemUser addSystemUser(@RequestBody SystemUser systemUser) {
        return systemUserService.save(systemUser);
    }

    @GetMapping
    public List<SystemUser> findAll() {
        return systemUserService.findAll();
    }
    @GetMapping("/{id}")
    public SystemUser findOne(@PathVariable Long id) {
        systemUserService.getSystemUserById(id);
        return systemUserService.getSystemUserById(id);
    }

    @PutMapping("/{id}")
    public SystemUser updateSystemUser(@PathVariable Long id, @RequestBody SystemUser systemUser) {
        systemUserService.update(id, systemUser);
        return systemUserService.getSystemUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSystemUser(@PathVariable Long id) {
        systemUserService.deleteById(id);
    }


}
