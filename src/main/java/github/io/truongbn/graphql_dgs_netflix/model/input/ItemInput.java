package github.io.truongbn.graphql_dgs_netflix.model.input;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemInput {
    private String name;
    private int inventory;
    private double price;
    private UUID shopId;
}
