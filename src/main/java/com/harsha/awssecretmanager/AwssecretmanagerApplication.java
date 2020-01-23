package com.harsha.awssecretmanager;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
@Configuration
public class AwssecretmanagerApplication {

	@Value("${app.db.host}")
	private String host;

	public static void main(String[] args) {

		SpringApplication.run(AwssecretmanagerApplication.class, args);
	}

	@Bean
	public DataSource getDataSource() throws JsonMappingException, JsonProcessingException {

		String secretName = "/secret/morning-api-rds-dev";

		AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard().withRegion("ap-southeast-1").build();

		GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);

		GetSecretValueResult getSecretValueResult = client.getSecretValue(getSecretValueRequest);

		System.out.println("secret retrieved ");
		final String secretBinaryString = getSecretValueResult.getSecretString();
		final ObjectMapper objectMapper = new ObjectMapper();

		final HashMap<String, String> secretMap = objectMapper.readValue(secretBinaryString, HashMap.class);

		HikariConfig config = new HikariConfig();
		config.setMaximumPoolSize(10);
		config.setMaxLifetime(30000);
		config.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
		config.addDataSourceProperty("serverName", host);
		config.addDataSourceProperty("port", "3306");
		config.addDataSourceProperty("databaseName", "dev_morning");
		config.addDataSourceProperty("user", secretMap.get("username"));
		config.addDataSourceProperty("password", secretMap.get("password"));

		return new HikariDataSource(config);
	}

}
