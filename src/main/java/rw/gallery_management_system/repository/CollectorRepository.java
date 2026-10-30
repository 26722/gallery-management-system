package rw.gallery_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.gallery_management_system.model.Collector;

import java.util.List;
import java.util.UUID;

public interface CollectorRepository extends JpaRepository<Collector, UUID> {

	List<Collector> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
}
