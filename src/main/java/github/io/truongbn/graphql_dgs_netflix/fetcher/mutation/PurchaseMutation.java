package github.io.truongbn.graphql_dgs_netflix.fetcher.mutation;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;

import github.io.truongbn.graphql_dgs_netflix.model.Customer;
import github.io.truongbn.graphql_dgs_netflix.model.Item;
import github.io.truongbn.graphql_dgs_netflix.model.Purchase;
import github.io.truongbn.graphql_dgs_netflix.model.input.PurchaseInput;
import github.io.truongbn.graphql_dgs_netflix.repository.CustomerRepository;
import github.io.truongbn.graphql_dgs_netflix.repository.ItemRepository;
import github.io.truongbn.graphql_dgs_netflix.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;

@DgsComponent
@RequiredArgsConstructor
public class PurchaseMutation {
    private final PurchaseRepository purchaseRepository;
    private final ItemRepository itemRepository;
    private final CustomerRepository customerRepository;
    @DgsData(parentType = "MutationResolver", field = "createPurchase")
    public Purchase createPurchase(@InputArgument("purchase") PurchaseInput purchase) {
        Customer customer = customerRepository.findById(purchase.getCustomerId())
                .orElseThrow(DgsEntityNotFoundException::new);
        Item item = itemRepository.findById(purchase.getItemId())
                .orElseThrow(DgsEntityNotFoundException::new);
        return purchaseRepository.save(Purchase.builder().customer(customer).item(item)
                .date(purchase.getDate()).quantity(purchase.getQuantity()).build());
    }
}
