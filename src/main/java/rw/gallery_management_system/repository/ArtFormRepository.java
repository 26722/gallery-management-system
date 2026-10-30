package rw.gallery_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rw.gallery_management_system.model.ArtForm;

import java.util.List;
import java.util.UUID;

public interface ArtFormRepository extends JpaRepository<ArtForm, UUID> {

	@Query("SELECT a FROM ArtForm a WHERE a.curators IS EMPTY")
	List<ArtForm> findUnused();
}
