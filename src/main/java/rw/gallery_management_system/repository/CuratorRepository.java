package rw.gallery_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.gallery_management_system.model.Curator;

import java.util.UUID;

public interface CuratorRepository extends JpaRepository<Curator, UUID> {
}
