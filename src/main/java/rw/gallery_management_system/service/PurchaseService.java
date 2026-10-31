package rw.gallery_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.gallery_management_system.model.PrivateViewing;
import rw.gallery_management_system.model.Purchase;
import rw.gallery_management_system.model.ViewingStatus;
import rw.gallery_management_system.repository.PrivateViewingRepository;
import rw.gallery_management_system.repository.PurchaseRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private PrivateViewingRepository privateViewingRepository;

	public String save(UUID privateViewingId, Purchase purchase) {
		Optional<PrivateViewing> privateViewing = privateViewingRepository.findById(privateViewingId);
		if (privateViewing.isEmpty()) {
			return "The private viewing with that id does not exist";
		}
		if (privateViewing.get().getStatus() != ViewingStatus.HELD) {
			return "Purchases are only for HELD private viewings";
		}
		purchase.setPurchaseDate(LocalDate.now());
		purchase.setPrivateViewing(privateViewing.get());
		purchaseRepository.save(purchase);
		return "Purchase saved successfully";
	}

	public List<Purchase> getByCollector(String collectorCode) {
		return purchaseRepository.findByCollectorCode(collectorCode);
	}

	public String delete(UUID id) {
		if (!purchaseRepository.existsById(id)) {
			return "The purchase with that id does not exist";
		}
		purchaseRepository.deleteById(id);
		return "Purchase deleted successfully";
	}
}
