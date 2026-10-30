package rw.gallery_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.gallery_management_system.model.ExhibitionHall;
import rw.gallery_management_system.repository.ExhibitionHallRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExhibitionHallService {

	@Autowired
	private ExhibitionHallRepository exhibitionHallRepository;

	public String save(ExhibitionHall exhibitionHall) {
		exhibitionHallRepository.save(exhibitionHall);
		return "Exhibition hall saved successfully";
	}

	public List<ExhibitionHall> getAll() {
		return exhibitionHallRepository.findAll();
	}

	public Optional<ExhibitionHall> getById(UUID id) {
		return exhibitionHallRepository.findById(id);
	}

	public String update(UUID id, ExhibitionHall exhibitionHall) {
		Optional<ExhibitionHall> existing = exhibitionHallRepository.findById(id);
		if (existing.isEmpty()) {
			return "The exhibition hall with that id does not exist";
		}
		ExhibitionHall found = existing.get();
		found.setHallNumber(exhibitionHall.getHallNumber());
		found.setName(exhibitionHall.getName());
		exhibitionHallRepository.save(found);
		return "Exhibition hall updated successfully";
	}

	public String delete(UUID id) {
		if (!exhibitionHallRepository.existsById(id)) {
			return "The exhibition hall with that id does not exist";
		}
		exhibitionHallRepository.deleteById(id);
		return "Exhibition hall deleted successfully";
	}

	public List<ExhibitionHall> getFree() {
		return exhibitionHallRepository.findFree();
	}
}
