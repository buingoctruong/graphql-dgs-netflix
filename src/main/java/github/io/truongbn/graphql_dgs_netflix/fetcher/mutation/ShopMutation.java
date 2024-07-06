package github.io.truongbn.graphql_dgs_netflix.fetcher.mutation;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import github.io.truongbn.graphql_dgs_netflix.model.Shop;
import github.io.truongbn.graphql_dgs_netflix.model.input.ShopInput;
import github.io.truongbn.graphql_dgs_netflix.repository.ShopRepository;
import lombok.RequiredArgsConstructor;

@DgsComponent
@RequiredArgsConstructor
public class ShopMutation {
    private final ShopRepository shopRepository;
    @DgsData(parentType = "MutationResolver", field = "createShop")
    public Shop createCustomer(@InputArgument("shop") ShopInput shop) {
        return shopRepository.save(Shop.builder().name(shop.getName()).email(shop.getEmail())
                .phone(shop.getPhone()).state(shop.getState()).city(shop.getCity())
                .street(shop.getStreet()).build());
    }
}
