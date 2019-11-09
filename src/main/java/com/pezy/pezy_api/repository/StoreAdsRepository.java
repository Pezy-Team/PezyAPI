package com.pezy.pezy_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pezy.pezy_api.entity.StoreAds;
import com.pezy.pezy_api.enumerate.AdsProcessStatusEnum;

@Repository
public interface StoreAdsRepository extends CrudRepository<StoreAds, Long>{
	
	public Page<StoreAds> findByOrderByCreateDateDesc(Pageable pageable);
	
	public Page<StoreAds> findByStatusOrderByCreateDateDesc(AdsProcessStatusEnum status, Pageable page);

}
