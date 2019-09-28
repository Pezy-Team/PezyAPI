package com.pezy.pezy_api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pezy.pezy_api.entity.Store;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long>{

}
