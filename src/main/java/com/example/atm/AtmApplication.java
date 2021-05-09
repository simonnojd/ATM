package com.example.atm;

import com.example.atm.models.Bill;
import com.example.atm.repositories.BillRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AtmApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtmApplication.class, args);
    }

    @Bean
    public CommandLineRunner atmMockData(BillRepository billRepository){

        return args -> {
           Bill bill_1000 = new Bill(1000, 2);
           Bill bill_500 = new Bill(500, 3);
           Bill bill_100 = new Bill(100, 5);

           billRepository.save(bill_1000);
           billRepository.save(bill_500);
           billRepository.save(bill_100);
        };
    }
}
