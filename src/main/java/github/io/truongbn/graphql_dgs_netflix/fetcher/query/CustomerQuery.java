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
import github.io.truongbn.graphql_dgs_netflix.model.Customer;
import github.io.truongbn.graphql_dgs_netflix.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@DgsComponent
@RequiredArgsConstructor
public class CustomerQuery {
    private final CustomerRepository customerRepository;
    private final CustomContextBuilder contextBuilder;
    @DgsData(parentType = "QueryResolver", field = "customers")
    public Iterable<Customer> findAll(DgsDataFetchingEnvironment dfe) {
        var customers = (List<Customer>) customerRepository.findAll();
        contextBuilder.customContext(null, null, customers, null).build();
        return customers;
    }

    @DgsData(parentType = "QueryResolver", field = "customer")
    public Customer findById(@InputArgument("id") String id, DgsDataFetchingEnvironment dfe) {
        CustomContext customContext = DgsContext.getCustomContext(dfe);
        var customers = customContext.getCustomers();
        if (CollectionUtils.isEmpty(customers)) {
            return customerRepository.findById(UUID.fromString(id))
                    .orElseThrow(DgsEntityNotFoundException::new);
        }
        var customer = customers.stream().filter(it -> it.getId().equals(UUID.fromString(id)))
                .findFirst();
        return customer.orElseGet(() -> customerRepository.findById(UUID.fromString(id))
                .orElseThrow(DgsEntityNotFoundException::new));
    }
}
