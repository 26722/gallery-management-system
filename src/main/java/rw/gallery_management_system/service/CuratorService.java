package rw.gallery_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.gallery_management_system.model.ArtForm;
import rw.gallery_management_system.model.Curator;
import rw.gallery_management_system.model.ExhibitionHall;
import rw.gallery_management_system.repository.ArtFormRepository;
import rw.gallery_management_system.repository.CuratorRepository;
import rw.gallery_management_system.repository.ExhibitionHallRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CuratorService {

	@Autowired
	private CuratorRepository curatorRepository;

	@Autowired
	private ArtFormRepository artFormRepository;

	@Autowired
	private ExhibitionHallRepository exhibitionHallRepository;

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

	public List<Curator> getByArtForm(String name) {
		return curatorRepository.findByArtFormName(name);
	}

	public String addArtForm(UUID curatorId, UUID artFormId) {
		Optional<Curator> curator = curatorRepository.findById(curatorId);
		if (curator.isEmpty()) {
			return "The curator with that id does not exist";
		}
		Optional<ArtForm> artForm = artFormRepository.findById(artFormId);
		if (artForm.isEmpty()) {
			return "That art form does not exist";
		}
		Curator found = curator.get();
		if (found.getArtForms().contains(artForm.get())) {
			return "The curator already has that art form";
		}
		found.getArtForms().add(artForm.get());
		curatorRepository.save(found);
		return "Art form added to the curator";
	}

	public String changeExhibitionHall(UUID curatorId, int hallNumber) {
		Optional<Curator> curator = curatorRepository.findById(curatorId);
		if (curator.isEmpty()) {
			return "The curator with that id does not exist";
		}
		Optional<ExhibitionHall> hall = exhibitionHallRepository.findByHallNumber(hallNumber);
		if (hall.isEmpty()) {
			return "The exhibition hall with that hallNumber does not exist";
		}
		if (curatorRepository.existsByExhibitionHallAndIdNot(hall.get(), curatorId)) {
			return "That exhibition hall is taken by another curator";
		}
		Curator found = curator.get();
		found.setExhibitionHall(hall.get());
		curatorRepository.save(found);
		return "Exhibition hall changed successfully";
	}
}
