package rw.gallery_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.gallery_management_system.model.ArtForm;

import java.util.UUID;

public interface ArtFormRepository extends JpaRepository<ArtForm, UUID> {
}
