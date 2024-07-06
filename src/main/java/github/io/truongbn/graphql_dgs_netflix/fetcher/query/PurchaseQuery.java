package github.io.truongbn.graphql_dgs_netflix.fetcher.query;

import java.util.UUID;

import org.springframework.util.CollectionUtils;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.context.DgsContext;

import github.io.truongbn.graphql_dgs_netflix.context.CustomContext;
import github.io.truongbn.graphql_dgs_netflix.model.Purchase;
import github.io.truongbn.graphql_dgs_netflix.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;

@DgsComponent
@RequiredArgsConstructor
public class PurchaseQuery {
    private final PurchaseRepository purchaseRepository;
    @DgsData(parentType = "QueryResolver", field = "purchases")
    public Iterable<Purchase> findByCustomerId(@InputArgument("customerId") String customerId,
            DgsDataFetchingEnvironment dfe) {
        CustomContext customContext = DgsContext.getCustomContext(dfe);
        var purchases = customContext.getPurchases();
        if (CollectionUtils.isEmpty(purchases)) {
            return purchaseRepository.findByCustomerId(UUID.fromString(customerId));
        }
        var optional = customContext.getPurchases().stream()
                .filter(it -> it.getCustomer().getId().equals(UUID.fromString(customerId)))
                .toList();
        return optional.isEmpty()
                ? purchaseRepository.findByCustomerId(UUID.fromString(customerId))
                : optional;
    }
}
