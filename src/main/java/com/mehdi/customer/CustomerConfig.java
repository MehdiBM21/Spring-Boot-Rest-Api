package com.mehdi.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class CustomerConfig {
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args -> {
            Customer leS = new Customer("john",
                    "jones@gmail.com",
                    LocalDate.of(1990, 11, 1));
            Customer leH = new Customer("dustin", "poirier@gmail.com",
                    LocalDate.of(1995, 9, 22));
            customerRepository.saveAll(List.of(leS, leH));
        };
    }
}
