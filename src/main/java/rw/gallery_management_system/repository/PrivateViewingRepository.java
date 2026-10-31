package rw.gallery_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import rw.gallery_management_system.model.PrivateViewing;
import rw.gallery_management_system.model.ViewingStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PrivateViewingRepository extends JpaRepository<PrivateViewing, UUID> {

	List<PrivateViewing> findByStatusOrderByViewingDateAsc(ViewingStatus status);

	List<PrivateViewing> findByViewingDateBetweenOrderByViewingDateAsc(LocalDate start, LocalDate end);

	boolean existsByCuratorIdAndViewingDateAndStatusNot(UUID curatorId, LocalDate viewingDate, ViewingStatus status);

	@Query("SELECT v.status, COUNT(v) FROM PrivateViewing v GROUP BY v.status")
	List<Object[]> countByStatus();

	@Modifying
	@Transactional
	@Query("UPDATE PrivateViewing v SET v.status = :cancelled WHERE v.curator.id = :curatorId AND v.viewingDate = :date AND v.status <> :held")
	int cancelDay(@Param("curatorId") UUID curatorId, @Param("date") LocalDate date, @Param("cancelled") ViewingStatus cancelled, @Param("held") ViewingStatus held);
}
