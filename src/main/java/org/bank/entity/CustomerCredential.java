package org.bank.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.bank.dto.Role;

@Entity
@Table(name = "customer_credentials")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class CustomerCredential extends BaseEntity {
    @Column(nullable = false, name = "password_hash")
    private String passwordHash;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false, name = "customer_id")
    private String customerId;
    @Enumerated(EnumType.STRING)
    private Role role;
}
