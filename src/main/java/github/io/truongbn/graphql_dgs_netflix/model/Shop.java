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
public class Shop {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String state;
    private String city;
    private String street;
    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private Set<Item> items;
}
