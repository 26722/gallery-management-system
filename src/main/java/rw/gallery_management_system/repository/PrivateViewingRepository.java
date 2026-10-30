package rw.gallery_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.gallery_management_system.model.PrivateViewing;
import rw.gallery_management_system.model.ViewingStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PrivateViewingRepository extends JpaRepository<PrivateViewing, UUID> {

	List<PrivateViewing> findByStatusOrderByViewingDateAsc(ViewingStatus status);

	List<PrivateViewing> findByViewingDateBetweenOrderByViewingDateAsc(LocalDate start, LocalDate end);
}
