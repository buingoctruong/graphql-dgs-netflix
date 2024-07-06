package github.io.truongbn.graphql_dgs_netflix.model.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerInput {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String state;
    private String city;
    private String street;
    private double amount;
}
