package io.pivotal.fe.retaildata.client.repositories;

import io.pivotal.fe.retaildata.client.domain.ProductSalesByStoreData;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

 

	@RepositoryRestResource(collectionResourceRel = "product_sales_by_store", path = "product_sales_by_store")
	public interface ProductSalesByStoreDataRepository extends PagingAndSortingRepository<ProductSalesByStoreData, Long> {

	    Page<ProductSalesByStoreData> findAll(Pageable pageable);

	    
	    @RestResource(path = "productNameContains", rel = "productNameContains")
		Page<ProductSalesByStoreData> findByProductNameContainsIgnoreCase(@Param("q") String productName, Pageable pageable);

		@RestResource(path = "storeId", rel = "storeId")
		Page<ProductSalesByStoreData> findByStoreId(@Param("q") int storeId, Pageable pageable);
	}
	
