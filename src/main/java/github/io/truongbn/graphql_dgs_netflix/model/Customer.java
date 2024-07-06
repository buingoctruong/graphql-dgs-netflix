package github.io.truongbn.graphql_dgs_netflix.model;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer {
    @Id
    @GeneratedValue
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String state;
    private String city;
    private String street;
    private double amount;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Purchase> items;
}
