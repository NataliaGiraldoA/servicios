package co.edu.usbcali.records.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "name",nullable = false,  length = 255, unique = false)
    private String name;
    @Column(name = "email",nullable = false,  length = 255, unique = true)
    private String email;
    @Column(name = "password_hash", nullable = false,  length = 255, unique = false)
    private String password;
    @Column(name = "phone_number",length = 255, unique = false)
    private String phoneNumber;
    @Column(name = "adress", length = 255, unique = false)
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false, updatable = false)
    private Date updatedAt;

}