//package com.harsha.bootstrapconfig;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//
//import com.amazonaws.services.simplesystemsmanagement.*;
//
//@Configuration
//@Order(value = 1)
//public class AwssecretmanagerApplicationBootStrapConfig {
//	
//	@Bean("smm")
//	public AWSSimpleSystemsManagement getAWSSimpleSystemsManagement() {
//		System.out.println("=========================AWSSimpleSystemsManagement======================");
//		var client = AWSSimpleSystemsManagementClientBuilder.standard().withRegion("ap-southeast-1").build();
//		return client;
//
//	}
//
//}
