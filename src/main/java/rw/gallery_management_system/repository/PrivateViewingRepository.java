package rw.gallery_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.gallery_management_system.model.PrivateViewing;

import java.util.UUID;

public interface PrivateViewingRepository extends JpaRepository<PrivateViewing, UUID> {
}
