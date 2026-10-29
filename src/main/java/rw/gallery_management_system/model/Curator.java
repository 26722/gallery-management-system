package rw.gallery_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "curator")
public class Curator {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String firstName;

	private String lastName;

	private String email;

	@ManyToOne
	@JoinColumn(name = "exhibition_hall_id")
	private ExhibitionHall exhibitionHall;

	@ManyToMany
	@JoinTable(name = "curator_art_form")
	private List<ArtForm> artForms;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ExhibitionHall getExhibitionHall() {
		return exhibitionHall;
	}

	public void setExhibitionHall(ExhibitionHall exhibitionHall) {
		this.exhibitionHall = exhibitionHall;
	}

	public List<ArtForm> getArtForms() {
		return artForms;
	}

	public void setArtForms(List<ArtForm> artForms) {
		this.artForms = artForms;
	}
}
