package rw.gallery_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.gallery_management_system.model.Collector;
import rw.gallery_management_system.repository.CollectorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CollectorService {

	@Autowired
	private CollectorRepository collectorRepository;

	public String save(Collector collector) {
		collectorRepository.save(collector);
		return "Collector saved successfully";
	}

	public List<Collector> getAll() {
		return collectorRepository.findAll();
	}

	public Optional<Collector> getById(UUID id) {
		return collectorRepository.findById(id);
	}

	public String update(UUID id, Collector collector) {
		Optional<Collector> existing = collectorRepository.findById(id);
		if (existing.isEmpty()) {
			return "The collector with that id does not exist";
		}
		Collector found = existing.get();
		found.setFirstName(collector.getFirstName());
		found.setLastName(collector.getLastName());
		found.setCollectorCode(collector.getCollectorCode());
		found.setEmail(collector.getEmail());
		found.setPhone(collector.getPhone());
		collectorRepository.save(found);
		return "Collector updated successfully";
	}

	public String delete(UUID id) {
		if (!collectorRepository.existsById(id)) {
			return "The collector with that id does not exist";
		}
		collectorRepository.deleteById(id);
		return "Collector deleted successfully";
	}

	public List<Collector> getByLastName(String lastName) {
		return collectorRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
	}
}
