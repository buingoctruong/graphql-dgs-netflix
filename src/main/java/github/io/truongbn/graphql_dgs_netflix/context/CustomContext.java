package github.io.truongbn.graphql_dgs_netflix.context;

import java.util.List;

import github.io.truongbn.graphql_dgs_netflix.model.Customer;
import github.io.truongbn.graphql_dgs_netflix.model.Item;
import github.io.truongbn.graphql_dgs_netflix.model.Purchase;
import github.io.truongbn.graphql_dgs_netflix.model.Shop;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomContext {
    private final List<Shop> shops;
    private final List<Item> items;
    private final List<Customer> customers;
    private final List<Purchase> purchases;
}
