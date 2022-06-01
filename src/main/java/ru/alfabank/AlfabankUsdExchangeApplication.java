package ru.alfabank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableDiscoveryClient
@EnableFeignClients(basePackages = "ru.alfabank.client")
@SpringBootApplication
public class AlfabankUsdExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlfabankUsdExchangeApplication.class, args);
	}

}
