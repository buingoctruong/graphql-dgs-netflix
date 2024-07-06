package github.io.truongbn.graphql_dgs_netflix.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Purchase {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Item item;
    private Instant date;
    private int quantity;
}
