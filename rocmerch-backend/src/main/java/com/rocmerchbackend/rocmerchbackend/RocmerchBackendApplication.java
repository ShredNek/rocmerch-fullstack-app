package com.rocmerchbackend.rocmerchbackend;

import com.rocmerchbackend.rocmerchbackend.model.MerchandiseItems;
import com.rocmerchbackend.rocmerchbackend.repository.MerchandiseItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class RocmerchBackendApplication  {

	@Autowired
	MerchandiseItemsRepository merchandiseItemsRepository;

	public static void main(String[] args) {
		SpringApplication.run(RocmerchBackendApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	private void saveCachedData() {
		UtilsFunctions.setCachedMerchItems(
		merchandiseItemsRepository.findAll());
		System.out.println( UtilsFunctions.getCachedMerchItems());
		UtilsFunctions.setCachedMerchItemMap();
	}

//	@EventListener(ApplicationReadyEvent.class)
//	private void blazingSaveCachedData() {
//		int lastId = 19; // Last ID in the database
//		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//		ArrayList<CompletableFuture<MerchandiseItems>> futures = new ArrayList<>();
//
//		for (long id = 1; id <= lastId; id++) {
//			long finalId = id;
//			CompletableFuture<MerchandiseItems> future = CompletableFuture.supplyAsync(() -> merchandiseItemsRepository.findById(finalId).orElse(null), executorService);
//			futures.add(future);
//		}
//
//		CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
//
//		List<MerchandiseItems> cachedItems = futures.stream()
//				.map(CompletableFuture::join)
//				.filter(Objects::nonNull)
//				.collect(Collectors.toList());
//
//		UtilsFunctions.setCachedMerchItems(cachedItems);
//		System.out.println(UtilsFunctions.getCachedMerchItems());
//	}
}