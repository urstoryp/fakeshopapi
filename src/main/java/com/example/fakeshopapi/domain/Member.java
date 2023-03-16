package com.example.fakeshopapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity // Database Table과 맵핑하는 객체.
@Table(name="member") // Database 테이블 이름 user3 와 User라는 객체가 맵핑.
@NoArgsConstructor // 기본생성자가 필요하다.
@Setter
@Getter
public class Member {
    @Id // 이 필드가 Table의 PK.
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // memberId는 자동으로 생성되도록 한다. 1,2,3,4
    private Long memberId;

    @Column(length = 255, unique = true)
    private String email;

    @Column(length = 50)
    private String name;

    @JsonIgnore
    @Column(length = 500)
    private String password;

    @CreationTimestamp // 현재시간이 저장될 때 자동으로 생성.
    private LocalDateTime regdate;

    @Column(nullable = false)
    private Integer birthYear;

    @Column(nullable = false)
    private Integer birthMonth;

    @Column(nullable = false)
    private Integer birthDay;

    @Column(length = 10, nullable = false)
    private String gender;

    @ManyToMany
    @JoinTable(name = "member_role",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "memberId=" + memberId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", regdate=" + regdate +
                ", birthYear=" + birthYear +
                ", birthMonth=" + birthMonth +
                ", birthDay=" + birthDay +
                ", gender='" + gender + '\'' +
                '}';
    }

    public void addRole(Role role) {
        roles.add(role);
    }
}

// User -----> Role