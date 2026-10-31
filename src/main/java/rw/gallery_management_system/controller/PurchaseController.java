package rw.gallery_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rw.gallery_management_system.model.Purchase;
import rw.gallery_management_system.service.PurchaseService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestParam UUID privateViewingId, @RequestBody Purchase purchase) {
		String message = purchaseService.save(privateViewingId, purchase);
		if (message.contains("does not exist")) {
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		if (message.contains("only for HELD")) {
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

	@GetMapping("/collector/{collectorCode}")
	public ResponseEntity<List<Purchase>> getByCollector(@PathVariable String collectorCode) {
		return new ResponseEntity<>(purchaseService.getByCollector(collectorCode), HttpStatus.OK);
	}
}
