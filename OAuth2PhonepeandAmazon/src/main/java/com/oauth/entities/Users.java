package com.oauth.entities;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "system_user_id")
    private Integer systemUserId;
    private String username;
    private String password;
    private String status;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Roles roles;
}
