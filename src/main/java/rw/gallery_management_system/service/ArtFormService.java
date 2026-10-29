package rw.gallery_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.gallery_management_system.model.ArtForm;
import rw.gallery_management_system.repository.ArtFormRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArtFormService {

	@Autowired
	private ArtFormRepository artFormRepository;

	public String save(ArtForm artForm) {
		artFormRepository.save(artForm);
		return "Art form saved successfully";
	}

	public List<ArtForm> getAll() {
		return artFormRepository.findAll();
	}

	public Optional<ArtForm> getById(UUID id) {
		return artFormRepository.findById(id);
	}

	public String update(UUID id, ArtForm artForm) {
		Optional<ArtForm> existing = artFormRepository.findById(id);
		if (existing.isEmpty()) {
			return "The art form with that id does not exist";
		}
		ArtForm found = existing.get();
		found.setName(artForm.getName());
		artFormRepository.save(found);
		return "Art form updated successfully";
	}

	public String delete(UUID id) {
		if (!artFormRepository.existsById(id)) {
			return "The art form with that id does not exist";
		}
		artFormRepository.deleteById(id);
		return "Art form deleted successfully";
	}
}
