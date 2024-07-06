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
public class Item {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private int inventory;
    private double price;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<Purchase> customers;
    @ManyToOne
    private Shop shop;
}
