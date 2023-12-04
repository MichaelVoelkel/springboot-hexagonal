package com.microservice.prices.springboot.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.microservice.prices.application.ports.api.PricesAPIService;
import com.microservice.prices.application.ports.spi.PricePersistance;
import com.microservice.prices.application.service.PriceService;


@SpringBootApplication(scanBasePackages = {"com.microservice.prices"})
@EnableJpaRepositories(basePackages={"com.microservice.prices.infrastructure.h2.repository"})
@ComponentScan(basePackages = { "com.microservice.*" }, basePackageClasses = {com.microservice.prices.domain.model.Price.class })
@EntityScan(basePackageClasses={com.microservice.prices.infrastructure.h2.entity.PriceEntity.class})
@EnableTransactionManagement
public class SpringbootRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestApplication.class, args);
	}	

	@Bean
	PricesAPIService pricesAPIService(PricePersistance pricePersistance) {
		return new PriceService(pricePersistance);
	}

    @Bean
    @Primary
    PricePersistance notherAdapter() {
        return new NotherAdapter();
    }

}
