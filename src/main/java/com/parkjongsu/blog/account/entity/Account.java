package com.parkjongsu.blog.account.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(unique = true)
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String username;

    @JsonIgnore
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    private String name;
    private String portrait;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime joinDate;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private Set<AccountRole> roles;


    public void passwordSetter(String password) {
        this.password = password;
    }
}
