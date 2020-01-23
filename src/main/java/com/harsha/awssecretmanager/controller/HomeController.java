package com.harsha.awssecretmanager.controller;

import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.harsha.awssecretmanager.entity.TestData;
import com.harsha.awssecretmanager.repo.HomeRepo;

@RestController
public class HomeController {

	@Autowired
	private HomeRepo homeRepo;

	@RequestMapping("/status")
	public Set<String> healthCheck() {
		return Collections.singleton("Status: OK");
	}

	@RequestMapping(value = "/getTestData/{id}", method = RequestMethod.GET)
	@ResponseBody
	public TestData getTestData(@PathVariable String id) {

		TestData testData;
		testData = homeRepo.findById(Long.parseLong(id));

		return testData;

	}

}
