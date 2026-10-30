package rw.gallery_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rw.gallery_management_system.model.Curator;
import rw.gallery_management_system.service.CuratorService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/curators")
public class CuratorController {

	@Autowired
	private CuratorService curatorService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Curator curator) {
		return new ResponseEntity<>(curatorService.save(curator), HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Curator>> getAll() {
		return new ResponseEntity<>(curatorService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable UUID id) {
		Optional<Curator> curator = curatorService.getById(id);
		if (curator.isPresent()) {
			return new ResponseEntity<>(curator.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("The curator with that id does not exist", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Curator curator) {
		String message = curatorService.update(id, curator);
		if (message.contains("does not exist")) {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id) {
		String message = curatorService.delete(id);
		if (message.contains("does not exist")) {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/by-art-form")
	public ResponseEntity<List<Curator>> getByArtForm(@RequestParam String name) {
		return new ResponseEntity<>(curatorService.getByArtForm(name), HttpStatus.OK);
	}

	@PutMapping("/{curatorId}/art-forms/{artFormId}")
	public ResponseEntity<String> addArtForm(@PathVariable UUID curatorId, @PathVariable UUID artFormId) {
		String message = curatorService.addArtForm(curatorId, artFormId);
		if (message.contains("does not exist")) {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		if (message.contains("already has")) {
			return new ResponseEntity<>(message, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@PatchMapping("/{curatorId}/exhibition-hall")
	public ResponseEntity<String> changeExhibitionHall(@PathVariable UUID curatorId, @RequestParam int hallNumber) {
		String message = curatorService.changeExhibitionHall(curatorId, hallNumber);
		if (message.contains("does not exist")) {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		if (message.contains("is taken")) {
			return new ResponseEntity<>(message, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
