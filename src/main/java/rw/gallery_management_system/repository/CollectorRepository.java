package rw.gallery_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rw.gallery_management_system.model.Collector;

import java.util.List;
import java.util.UUID;

public interface CollectorRepository extends JpaRepository<Collector, UUID> {

	List<Collector> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

	@Query("SELECT DISTINCT v.collector FROM PrivateViewing v WHERE v.curator.id = :curatorId")
	List<Collector> findByCuratorId(@Param("curatorId") UUID curatorId);
}
