package github.io.truongbn.graphql_dgs_netflix.fetcher.mutation;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;

import github.io.truongbn.graphql_dgs_netflix.model.Item;
import github.io.truongbn.graphql_dgs_netflix.model.Shop;
import github.io.truongbn.graphql_dgs_netflix.model.input.ItemInput;
import github.io.truongbn.graphql_dgs_netflix.repository.ItemRepository;
import github.io.truongbn.graphql_dgs_netflix.repository.ShopRepository;
import lombok.RequiredArgsConstructor;

@DgsComponent
@RequiredArgsConstructor
public class ItemMutation {
    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;
    @DgsData(parentType = "MutationResolver", field = "createItem")
    public Item createItem(@InputArgument("item") ItemInput item) {
        Shop shop = shopRepository.findById(item.getShopId())
                .orElseThrow(DgsEntityNotFoundException::new);
        return itemRepository.save(Item.builder().name(item.getName())
                .inventory(item.getInventory()).price(item.getPrice()).shop(shop).build());
    }
}
