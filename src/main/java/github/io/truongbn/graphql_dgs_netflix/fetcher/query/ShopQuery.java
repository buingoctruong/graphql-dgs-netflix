package github.io.truongbn.graphql_dgs_netflix.fetcher.query;

import java.util.List;
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
import github.io.truongbn.graphql_dgs_netflix.model.Shop;
import github.io.truongbn.graphql_dgs_netflix.repository.ShopRepository;
import lombok.RequiredArgsConstructor;

@DgsComponent
@RequiredArgsConstructor
public class ShopQuery {
    private final ShopRepository shopRepository;
    private final CustomContextBuilder contextBuilder;
    @DgsData(parentType = "QueryResolver", field = "shops")
    public Iterable<Shop> findAll(DgsDataFetchingEnvironment dfe) {
        var shops = (List<Shop>) shopRepository.findAll();
        contextBuilder.customContext(shops, null, null, null).build();
        return shops;
    }

    @DgsData(parentType = "QueryResolver", field = "shop")
    public Shop findById(@InputArgument("id") String id, DgsDataFetchingEnvironment dfe) {
        CustomContext customContext = DgsContext.getCustomContext(dfe);
        var shops = customContext.getShops();
        if (CollectionUtils.isEmpty(shops)) {
            return shopRepository.findById(UUID.fromString(id))
                    .orElseThrow(DgsEntityNotFoundException::new);
        }
        var shop = shops.stream().filter(it -> it.getId().equals(UUID.fromString(id))).findFirst();
        return shop.orElseGet(() -> shopRepository.findById(UUID.fromString(id))
                .orElseThrow(DgsEntityNotFoundException::new));
    }
}
