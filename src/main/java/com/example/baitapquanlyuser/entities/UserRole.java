package com.example.baitapquanlyuser.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "user_roles", catalog = "test",
        uniqueConstraints = @UniqueConstraint(
                columnNames = { "role", "user_id" }))
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id", nullable = false)
    @NotNull
    private Integer userRoleId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Valid
    @NotNull
    private TblUser tblUser;
    @Column(name = "role", length = 45, nullable = false)
    @NotNull
    private String role;
}
