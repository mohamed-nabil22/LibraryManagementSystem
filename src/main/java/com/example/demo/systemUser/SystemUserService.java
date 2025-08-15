package com.example.demo.systemUser;


import com.example.demo.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemUserService {

    @Autowired
    private SystemUserRepository systemUserRepository;

    // Create or update a user
    public SystemUser save(SystemUser systemUser) {
        return systemUserRepository.save(systemUser); // Save or update the user
    }
//get all
    public List<SystemUser> findAll() {
        return systemUserRepository.findAll();
    }
    //getbyid
    public SystemUser getSystemUserById(Long id) {
        SystemUser systemUser = systemUserRepository.findById(id).get();
        if(systemUser == null) {
            throw new IllegalStateException("SystemUser with Id " + id + " does not exist.");
        }
        return systemUser;
    }
    public SystemUser findByUsername(String username) {
        Optional<SystemUser> exist = systemUserRepository.findByUsername(username);
        if(exist.isPresent()) {
            return exist.get();
        }
        else
            throw new IllegalStateException("User with name " + username + " not found");
    }

    public SystemUser update(Long id, SystemUser systemUser) {
        SystemUser existingUser = systemUserRepository.findUserById(id);
        if(existingUser == null) {
            throw new IllegalStateException("SystemUser with Id " + id + " does not exist.");
        }
        existingUser.setUsername(systemUser.getUsername());
        existingUser.setPassword(systemUser.getPassword());
        existingUser.setEmail(systemUser.getEmail());
        existingUser.setRole(systemUser.getRole());
        return systemUserRepository.save(existingUser);

    }
    public void deleteById(Long id) {
        if (systemUserRepository.existsById(id)) {
            systemUserRepository.deleteById(id);
        }
       else
           throw new IllegalStateException("User with id " + id + " not found");
    }


}
