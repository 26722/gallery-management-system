package rw.gallery_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import rw.gallery_management_system.model.PrivateViewing;
import rw.gallery_management_system.model.ViewingStatus;
import rw.gallery_management_system.service.PrivateViewingService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/private-viewings")
public class PrivateViewingController {

	@Autowired
	private PrivateViewingService privateViewingService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody PrivateViewing privateViewing) {
		String message = privateViewingService.save(privateViewing);
		if (message.contains("in the past")) {
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		if (message.contains("already hosts")) {
			return new ResponseEntity<>(message, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<PrivateViewing>> getAll() {
		return new ResponseEntity<>(privateViewingService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable UUID id) {
		Optional<PrivateViewing> privateViewing = privateViewingService.getById(id);
		if (privateViewing.isPresent()) {
			return new ResponseEntity<>(privateViewing.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>("The private viewing with that id does not exist", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody PrivateViewing privateViewing) {
		String message = privateViewingService.update(id, privateViewing);
		if (message.contains("does not exist")) {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id) {
		String message = privateViewingService.delete(id);
		if (message.contains("does not exist")) {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		if (message.contains("has purchases")) {
			return new ResponseEntity<>(message, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@PatchMapping("/{id}/status")
	public ResponseEntity<String> changeStatus(@PathVariable UUID id, @RequestParam ViewingStatus status) {
		String message = privateViewingService.changeStatus(id, status);
		if (message.contains("does not exist")) {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		if (message.startsWith("Cannot change")) {
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/page")
	public ResponseEntity<Page<PrivateViewing>> getPage(@RequestParam int page, @RequestParam int size, @RequestParam String sort) {
		String[] parts = sort.split(",");
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(parts[1]), parts[0]));
		return new ResponseEntity<>(privateViewingService.getPage(pageable), HttpStatus.OK);
	}

	@GetMapping("/stats/by-status")
	public ResponseEntity<List<Object[]>> countByStatus() {
		return new ResponseEntity<>(privateViewingService.countByStatus(), HttpStatus.OK);
	}

	@PatchMapping("/cancel-day")
	public ResponseEntity<String> cancelDay(@RequestParam UUID curatorId, @RequestParam String date) {
		return new ResponseEntity<>(privateViewingService.cancelDay(curatorId, LocalDate.parse(date)), HttpStatus.OK);
	}

	@GetMapping("/by-status")
	public ResponseEntity<List<PrivateViewing>> getByStatus(@RequestParam ViewingStatus status) {
		return new ResponseEntity<>(privateViewingService.getByStatus(status), HttpStatus.OK);
	}

	@GetMapping("/between")
	public ResponseEntity<List<PrivateViewing>> getBetween(@RequestParam String start, @RequestParam String end) {
		return new ResponseEntity<>(privateViewingService.getBetween(LocalDate.parse(start), LocalDate.parse(end)), HttpStatus.OK);
	}
}
