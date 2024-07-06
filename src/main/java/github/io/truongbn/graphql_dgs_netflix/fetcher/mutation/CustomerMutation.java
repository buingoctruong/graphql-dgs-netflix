package github.io.truongbn.graphql_dgs_netflix.fetcher.mutation;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import github.io.truongbn.graphql_dgs_netflix.model.Customer;
import github.io.truongbn.graphql_dgs_netflix.model.input.CustomerInput;
import github.io.truongbn.graphql_dgs_netflix.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@DgsComponent
@RequiredArgsConstructor
public class CustomerMutation {
    private final CustomerRepository customerRepository;
    @DgsData(parentType = "MutationResolver", field = "createCustomer")
    public Customer createCustomer(@InputArgument("customer") CustomerInput customer) {
        return customerRepository.save(Customer.builder().firstName(customer.getFirstName())
                .lastName(customer.getLastName()).email(customer.getEmail())
                .phone(customer.getPhone()).state(customer.getState()).city(customer.getCity())
                .street(customer.getStreet()).amount(customer.getAmount()).build());
    }
}
