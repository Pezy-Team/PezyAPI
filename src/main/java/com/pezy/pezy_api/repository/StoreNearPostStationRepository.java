package com.pezy.pezy_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pezy.pezy_api.entity.StoreNearPostStation;

@Repository
public interface StoreNearPostStationRepository extends CrudRepository<StoreNearPostStation, Long>{

}
