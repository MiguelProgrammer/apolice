package com.acme.apolice;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class ApoliceApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SpringApplication.run(ApoliceApplication.class, args);
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092"); // listener externo
        try (AdminClient client = AdminClient.create(config)) {
            ListTopicsResult topics = client.listTopics();
            topics.names().get().forEach(System.out::println);
        }
	}
}
