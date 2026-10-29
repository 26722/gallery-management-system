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
import rw.gallery_management_system.model.ExhibitionHall;
import rw.gallery_management_system.service.ExhibitionHallService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/exhibition-halls")
public class ExhibitionHallController {

	@Autowired
	private ExhibitionHallService exhibitionHallService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody ExhibitionHall exhibitionHall) {
		return new ResponseEntity<>(exhibitionHallService.save(exhibitionHall), HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ExhibitionHall>> getAll() {
		return new ResponseEntity<>(exhibitionHallService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable UUID id) {
		Optional<ExhibitionHall> exhibitionHall = exhibitionHallService.getById(id);
		if (exhibitionHall.isPresent()) {
			return new ResponseEntity<>(exhibitionHall.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("The exhibition hall with that id does not exist", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody ExhibitionHall exhibitionHall) {
		String message = exhibitionHallService.update(id, exhibitionHall);
		if (message.contains("does not exist")) {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id) {
		String message = exhibitionHallService.delete(id);
		if (message.contains("does not exist")) {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
