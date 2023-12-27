package com.lms.service.impl;

import com.lms.model.entities.User;
import com.lms.model.entities.Role;
import com.lms.repository.RoleRepository;
import com.lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        Set<Role> rolesSet = new HashSet<>();
        user.setPassword(encoder.encode(user.getPassword()));
        rolesSet.add(roleRepository.getRoleByName(user.getRoleName().toUpperCase()));
        user.setRoles(rolesSet);
        userRepository.save(user);
    }

    public User getByName(String name) {
        return userRepository.getUserByName(name);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found Company by id" + id));
    }

    public void update(Long id, User user, String roleName) {
        User oldUser = getUserById(id);
        oldUser.setRole(user.getRole());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setIsActive(user.getIsActive());
        oldUser.setIsDeleted(user.getIsDeleted());
        oldUser.setLocalDate(user.getLocalDate());
        Role role = roleRepository.getRoleByName(roleName);
        Set<Role> rolesSet = new HashSet<>();
        rolesSet.add(role);
        oldUser.setRoles(rolesSet);
        oldUser.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(oldUser);

    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}















































//    private final BCryptPasswordEncoder passwordEncoder;
//    private final UserRepository userRepository;
////    private final RoleRepository roleRepository;
//
//    public void create(User user){
//        userRepository.save(user);
//    }
//
//    public User findById(Long id){
//        return userRepository.findById(id)
//                .orElseThrow(()->new RuntimeException("Not found Company by id"+id));
//    }
//    public List<User> findAll(){
//        return userRepository.findAll();
//    }
//
//    public void update(Long id, User user){
//        User oldUser = findById(id);
//        oldUser.setRole(user.getRole());
//        oldUser.setFirstName(user.getFirstName());
//        oldUser.setLastName(user.getLastName());
//        oldUser.setPassword(user.getPassword());
//        oldUser.setEmail(user.getEmail());
//        oldUser.setIsActive(user.getIsActive());
//        oldUser.setIsDeleted(user.getIsDeleted());
//        oldUser.setLocalDate(user.getLocalDate());
//        userRepository.save(oldUser);
//    }
//
//    public void deleteById(Long id){
//        userRepository.deleteById(id);
//    }


//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public void addUser(User user) {
//        Set<Role> rolesSet = new HashSet<>();
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        rolesSet.add(roleRepository.getRoleByName(user.getRoleName().toUpperCase()));
//        user.setRoles(rolesSet);
//        userRepository.save(user);
//    }
//    public User getByName(String name){
//        return userRepository.getUserByName(name);
//    }
//    public void updateUser(Long id,User user,String roleName){
//
//    }


