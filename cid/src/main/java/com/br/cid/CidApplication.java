package com.br.cid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = "com.br.cid")
public class CidApplication {

	public static void main(String[] args) {
		SpringApplication.run(CidApplication.class, args);
	}

}
