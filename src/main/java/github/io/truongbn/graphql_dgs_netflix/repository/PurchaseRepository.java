package github.io.truongbn.graphql_dgs_netflix.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import github.io.truongbn.graphql_dgs_netflix.model.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
    List<Purchase> findByCustomerId(UUID customerId);
}
