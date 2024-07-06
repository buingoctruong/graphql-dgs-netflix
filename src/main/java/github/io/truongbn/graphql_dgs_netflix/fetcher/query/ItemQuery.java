package github.io.truongbn.graphql_dgs_netflix.fetcher.query;

import java.util.UUID;

import org.springframework.util.CollectionUtils;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.context.DgsContext;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;

import github.io.truongbn.graphql_dgs_netflix.context.CustomContext;
import github.io.truongbn.graphql_dgs_netflix.context.CustomContextBuilder;
import github.io.truongbn.graphql_dgs_netflix.model.Item;
import github.io.truongbn.graphql_dgs_netflix.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@DgsComponent
@RequiredArgsConstructor
public class ItemQuery {
    private final ItemRepository itemRepository;
    private final CustomContextBuilder customContextBuilder;
    @DgsData(parentType = "QueryResolver", field = "items")
    public Iterable<Item> findAll(DgsDataFetchingEnvironment dfe) {
        var items = itemRepository.findAll();
        customContextBuilder.customContext(null, items, null, null).build();
        return items;
    }

    @DgsData(parentType = "QueryResolver", field = "item")
    public Item findById(@InputArgument("id") String id, DgsDataFetchingEnvironment dfe) {
        CustomContext customContext = DgsContext.getCustomContext(dfe);
        var items = customContext.getItems();
        if (CollectionUtils.isEmpty(items)) {
            return itemRepository.findById(UUID.fromString(id))
                    .orElseThrow(DgsEntityNotFoundException::new);
        }
        var item = items.stream().filter(it -> it.getId().equals(UUID.fromString(id))).findFirst();
        return item.orElseGet(() -> itemRepository.findById(UUID.fromString(id))
                .orElseThrow(DgsEntityNotFoundException::new));
    }
}
