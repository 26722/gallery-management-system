package rw.gallery_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.gallery_management_system.model.Curator;
import rw.gallery_management_system.repository.CuratorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CuratorService {

	@Autowired
	private CuratorRepository curatorRepository;

	public String save(Curator curator) {
		curatorRepository.save(curator);
		return "Curator saved successfully";
	}

	public List<Curator> getAll() {
		return curatorRepository.findAll();
	}

	public Optional<Curator> getById(UUID id) {
		return curatorRepository.findById(id);
	}

	public String update(UUID id, Curator curator) {
		Optional<Curator> existing = curatorRepository.findById(id);
		if (existing.isEmpty()) {
			return "The curator with that id does not exist";
		}
		Curator found = existing.get();
		found.setFirstName(curator.getFirstName());
		found.setLastName(curator.getLastName());
		found.setEmail(curator.getEmail());
		found.setExhibitionHall(curator.getExhibitionHall());
		found.setArtForms(curator.getArtForms());
		curatorRepository.save(found);
		return "Curator updated successfully";
	}

	public String delete(UUID id) {
		if (!curatorRepository.existsById(id)) {
			return "The curator with that id does not exist";
		}
		curatorRepository.deleteById(id);
		return "Curator deleted successfully";
	}
}
