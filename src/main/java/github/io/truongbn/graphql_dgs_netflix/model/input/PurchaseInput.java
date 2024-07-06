package github.io.truongbn.graphql_dgs_netflix.model.input;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseInput {
    private UUID customerId;
    private UUID itemId;
    private Instant date;
    private int quantity;
}
