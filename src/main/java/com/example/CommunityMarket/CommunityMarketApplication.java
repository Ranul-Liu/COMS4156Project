package com.example.CommunityMarket;

import com.example.CommunityMarket.config.RsaKeyProperties;
import com.example.CommunityMarket.model.Item;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
@RestController
public class CommunityMarketApplication {

	public static void main(String[] args) {

		SpringApplication.run(CommunityMarketApplication.class, args);
	}



}
