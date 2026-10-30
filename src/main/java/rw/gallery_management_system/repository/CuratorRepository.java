package rw.gallery_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rw.gallery_management_system.model.Curator;

import java.util.List;
import java.util.UUID;

public interface CuratorRepository extends JpaRepository<Curator, UUID> {

	@Query("SELECT DISTINCT c FROM Curator c JOIN c.artForms a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<Curator> findByArtFormName(@Param("name") String name);
}
