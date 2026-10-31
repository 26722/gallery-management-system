package rw.gallery_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rw.gallery_management_system.model.Purchase;

import java.util.List;
import java.util.UUID;

public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {

	@Query("SELECT p FROM Purchase p WHERE p.privateViewing.collector.collectorCode = :collectorCode ORDER BY p.purchaseDate DESC")
	List<Purchase> findByCollectorCode(@Param("collectorCode") String collectorCode);
}
