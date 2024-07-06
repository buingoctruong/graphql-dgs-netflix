package github.io.truongbn.graphql_dgs_netflix.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import github.io.truongbn.graphql_dgs_netflix.model.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, UUID> {
}
