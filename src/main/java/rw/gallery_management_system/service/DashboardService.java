package rw.gallery_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.gallery_management_system.model.ViewingStatus;
import rw.gallery_management_system.repository.CollectorRepository;
import rw.gallery_management_system.repository.CuratorRepository;
import rw.gallery_management_system.repository.ExhibitionHallRepository;
import rw.gallery_management_system.repository.PrivateViewingRepository;
import rw.gallery_management_system.repository.PurchaseRepository;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {

	@Autowired
	private CollectorRepository collectorRepository;

	@Autowired
	private CuratorRepository curatorRepository;

	@Autowired
	private ExhibitionHallRepository exhibitionHallRepository;

	@Autowired
	private PrivateViewingRepository privateViewingRepository;

	@Autowired
	private PurchaseRepository purchaseRepository;

	public Map<String, Long> getSummary() {
		Map<String, Long> summary = new LinkedHashMap<>();
		summary.put("collectors", collectorRepository.count());
		summary.put("curators", curatorRepository.count());
		summary.put("freeExhibitionHalls", (long) exhibitionHallRepository.findFree().size());
		summary.put("viewingsToday", privateViewingRepository.countByViewingDate(LocalDate.now()));
		summary.put("requested", privateViewingRepository.countByStatus(ViewingStatus.REQUESTED));
		summary.put("purchases", purchaseRepository.count());
		return summary;
	}
}
