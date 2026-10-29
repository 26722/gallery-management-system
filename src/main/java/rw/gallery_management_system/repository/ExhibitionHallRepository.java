package rw.gallery_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.gallery_management_system.model.ExhibitionHall;

import java.util.UUID;

public interface ExhibitionHallRepository extends JpaRepository<ExhibitionHall, UUID> {
}
