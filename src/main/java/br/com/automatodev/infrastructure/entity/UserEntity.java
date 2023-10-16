package br.com.automatodev.infrastructure.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity(name = "tb_users")
public class UserEntity {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "name")
    private String name;

    @Column(name = "secret")
    private String password;

    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    
    List<TaskEntity> tasks;
}
