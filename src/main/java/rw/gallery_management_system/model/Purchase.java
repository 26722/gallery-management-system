package rw.gallery_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "purchase")
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String artworkTitle;

	private String remarks;

	private int priceUsd;

	private LocalDate purchaseDate;

	@ManyToOne
	@JoinColumn(name = "private_viewing_id")
	private PrivateViewing privateViewing;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getArtworkTitle() {
		return artworkTitle;
	}

	public void setArtworkTitle(String artworkTitle) {
		this.artworkTitle = artworkTitle;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getPriceUsd() {
		return priceUsd;
	}

	public void setPriceUsd(int priceUsd) {
		this.priceUsd = priceUsd;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public PrivateViewing getPrivateViewing() {
		return privateViewing;
	}

	public void setPrivateViewing(PrivateViewing privateViewing) {
		this.privateViewing = privateViewing;
	}
}
