package rw.gallery_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rw.gallery_management_system.model.Collector;
import rw.gallery_management_system.service.CollectorService;
import rw.gallery_management_system.service.CuratorService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/collectors")
public class CollectorController {

	@Autowired
	private CollectorService collectorService;

	@Autowired
	private CuratorService curatorService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Collector collector) {
		return new ResponseEntity<>(collectorService.save(collector), HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Collector>> getAll() {
		return new ResponseEntity<>(collectorService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable UUID id) {
		Optional<Collector> collector = collectorService.getById(id);
		if (collector.isPresent()) {
			return new ResponseEntity<>(collector.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("The collector with that id does not exist", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Collector collector) {
		String message = collectorService.update(id, collector);
		if (message.contains("does not exist")) {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id) {
		String message = collectorService.delete(id);
		if (message.contains("does not exist")) {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/by-last-name")
	public ResponseEntity<List<Collector>> getByLastName(@RequestParam String lastName) {
		return new ResponseEntity<>(collectorService.getByLastName(lastName), HttpStatus.OK);
	}

	@GetMapping("/of-curator/{curatorId}")
	public ResponseEntity<?> getOfCurator(@PathVariable UUID curatorId) {
		if (curatorService.getById(curatorId).isEmpty()) {
			return new ResponseEntity<>("The curator with that id does not exist", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(collectorService.getOfCurator(curatorId), HttpStatus.OK);
	}
}
