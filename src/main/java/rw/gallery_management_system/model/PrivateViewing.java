package rw.gallery_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "private_viewing")
public class PrivateViewing {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private LocalDate viewingDate;

	@Enumerated(EnumType.STRING)
	private ViewingStatus status;

	private String interest;

	@ManyToOne
	@JoinColumn(name = "collector_id")
	private Collector collector;

	@ManyToOne
	@JoinColumn(name = "curator_id")
	private Curator curator;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDate getViewingDate() {
		return viewingDate;
	}

	public void setViewingDate(LocalDate viewingDate) {
		this.viewingDate = viewingDate;
	}

	public ViewingStatus getStatus() {
		return status;
	}

	public void setStatus(ViewingStatus status) {
		this.status = status;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public Collector getCollector() {
		return collector;
	}

	public void setCollector(Collector collector) {
		this.collector = collector;
	}

	public Curator getCurator() {
		return curator;
	}

	public void setCurator(Curator curator) {
		this.curator = curator;
	}
}
