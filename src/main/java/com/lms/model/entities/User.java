package com.lms.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lms.model.enums.StudyFormation;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int age;
    private Boolean isActive = true;
    private Boolean isDeleted = false;

    @CreatedDate
    private LocalDate localDate;
    private Role roleName;
    @Column(name = "study_format")
    @Enumerated(EnumType.STRING)
    private StudyFormation studyFormation;
    @OneToOne(cascade = {CascadeType.PERSIST,
                CascadeType.REFRESH,
                CascadeType.DETACH,
                CascadeType.MERGE})
    @JsonIgnore
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST})
    @JsonIgnore
    private Group group;
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(roleName.getAuthority()));
        System.out.println("User");
        System.out.println(roleName.getAuthority());
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Transient
    private String role;
}
