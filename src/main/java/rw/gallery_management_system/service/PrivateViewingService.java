package rw.gallery_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.gallery_management_system.model.PrivateViewing;
import rw.gallery_management_system.repository.PrivateViewingRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PrivateViewingService {

	@Autowired
	private PrivateViewingRepository privateViewingRepository;

	public String save(PrivateViewing privateViewing) {
		privateViewingRepository.save(privateViewing);
		return "Private viewing saved successfully";
	}

	public List<PrivateViewing> getAll() {
		return privateViewingRepository.findAll();
	}

	public Optional<PrivateViewing> getById(UUID id) {
		return privateViewingRepository.findById(id);
	}

	public String update(UUID id, PrivateViewing privateViewing) {
		Optional<PrivateViewing> existing = privateViewingRepository.findById(id);
		if (existing.isEmpty()) {
			return "The private viewing with that id does not exist";
		}
		PrivateViewing found = existing.get();
		found.setViewingDate(privateViewing.getViewingDate());
		found.setStatus(privateViewing.getStatus());
		found.setInterest(privateViewing.getInterest());
		found.setCollector(privateViewing.getCollector());
		found.setCurator(privateViewing.getCurator());
		privateViewingRepository.save(found);
		return "Private viewing updated successfully";
	}

	public String delete(UUID id) {
		if (!privateViewingRepository.existsById(id)) {
			return "The private viewing with that id does not exist";
		}
		privateViewingRepository.deleteById(id);
		return "Private viewing deleted successfully";
	}
}
