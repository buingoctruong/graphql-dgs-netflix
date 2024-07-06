package github.io.truongbn.graphql_dgs_netflix.context;

import java.util.List;

import org.springframework.stereotype.Component;

import com.netflix.graphql.dgs.context.DgsCustomContextBuilder;

import github.io.truongbn.graphql_dgs_netflix.model.Customer;
import github.io.truongbn.graphql_dgs_netflix.model.Item;
import github.io.truongbn.graphql_dgs_netflix.model.Purchase;
import github.io.truongbn.graphql_dgs_netflix.model.Shop;

@Component
public class CustomContextBuilder implements DgsCustomContextBuilder<CustomContext> {
    private List<Shop> shops;
    private List<Item> items;
    private List<Customer> customers;
    private List<Purchase> purchases;
    public CustomContextBuilder customContext(List<Shop> shops, List<Item> items,
            List<Customer> customers, List<Purchase> purchases) {
        this.shops = shops;
        this.items = items;
        this.customers = customers;
        this.purchases = purchases;
        return this;
    }

    @Override
    public CustomContext build() {
        return new CustomContext(shops, items, customers, purchases);
    }
}
