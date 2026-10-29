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
import org.springframework.web.bind.annotation.RestController;
import rw.gallery_management_system.model.ArtForm;
import rw.gallery_management_system.service.ArtFormService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/art-forms")
public class ArtFormController {

	@Autowired
	private ArtFormService artFormService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody ArtForm artForm) {
		return new ResponseEntity<>(artFormService.save(artForm), HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ArtForm>> getAll() {
		return new ResponseEntity<>(artFormService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable UUID id) {
		Optional<ArtForm> artForm = artFormService.getById(id);
		if (artForm.isPresent()) {
			return new ResponseEntity<>(artForm.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("The art form with that id does not exist", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody ArtForm artForm) {
		String message = artFormService.update(id, artForm);
		if (message.contains("does not exist")) {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id) {
		String message = artFormService.delete(id);
		if (message.contains("does not exist")) {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
