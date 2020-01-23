package com.harsha.awssecretmanager.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.harsha.awssecretmanager.entity.TestData;

@Repository
public interface HomeRepo extends CrudRepository<TestData, Long> {
	TestData findById(long id);
}

