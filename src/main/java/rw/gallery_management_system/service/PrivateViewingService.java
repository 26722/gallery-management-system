package rw.gallery_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.gallery_management_system.model.PrivateViewing;
import rw.gallery_management_system.model.ViewingStatus;
import rw.gallery_management_system.repository.PrivateViewingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PrivateViewingService {

	@Autowired
	private PrivateViewingRepository privateViewingRepository;

	public String save(PrivateViewing privateViewing) {
		if (privateViewing.getViewingDate().isBefore(LocalDate.now())) {
			return "Private viewing date cannot be in the past";
		}
		if (privateViewingRepository.existsByCuratorIdAndViewingDateAndStatusNot(privateViewing.getCurator().getId(), privateViewing.getViewingDate(), ViewingStatus.CANCELLED)) {
			return "Curator already hosts a viewing on that date";
		}
		privateViewing.setStatus(ViewingStatus.REQUESTED);
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

	public List<PrivateViewing> getByStatus(ViewingStatus status) {
		return privateViewingRepository.findByStatusOrderByViewingDateAsc(status);
	}

	public List<PrivateViewing> getBetween(LocalDate start, LocalDate end) {
		return privateViewingRepository.findByViewingDateBetweenOrderByViewingDateAsc(start, end);
	}
}
